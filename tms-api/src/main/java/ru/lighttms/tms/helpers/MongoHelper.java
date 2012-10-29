package ru.lighttms.tms.helpers;

import com.mongodb.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.lighttms.tms.beans.*;
import ru.lighttms.tms.utils.UserUtils;

import java.util.*;
import java.util.logging.Logger;


/**
* Created by IntelliJ IDEA.
* User: azee
* Date: 4/25/12
* Time: 9:52 PM
*/
public class MongoHelper {

    private static Logger log = Logger.getLogger(MongoHelper.class.getName());

    private static MongoOperations mongoOperation;

    public static synchronized void reconnectToMongo() throws Exception {
        if (mongoOperation == null){
            initMongoOperation();
        }
    }

    public static synchronized MongoOperations getMongoOperations() throws Exception {
        if (mongoOperation == null){
            initMongoOperation();
        }
        return mongoOperation;
    }

   /**
    * Projects
    */
    public static List<Project> getAllProjects() throws Exception {
        reconnectToMongo();
        return mongoOperation.findAll(Project.class, "tms_project");
    }

    public static Project getProject(String id) throws Exception {
        reconnectToMongo();
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperation.findOne(query, Project.class, "tms_project");
    }

    public static void createProject(Project project) throws Exception {
        reconnectToMongo();
        mongoOperation.insert(project, "tms_project");
    }


    public static void updateProject(Project project) throws Exception {
        reconnectToMongo();
        //Removing old tests
        Query query = new Query();
        mongoOperation.save(project, "tms_project");
    }

    public static void removeProject(String id) throws Exception {
        Project project = getProject(id);
        reconnectToMongo();

        //Removing old tests
        mongoOperation.remove(project, "tms_project");
    }


    /**
    * Suites
    */
    public static List<Suite> getAllSuites() throws Exception {
        reconnectToMongo();
        return mongoOperation.findAll(Suite.class, "tms_suite");
    }

    public static Suite getSuite(String id) throws Exception {
        reconnectToMongo();
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperation.findOne(query, Suite.class, "tms_suite");
    }

    public static void createSuite(Suite suite) throws Exception {
        reconnectToMongo();
        mongoOperation.insert(suite, "tms_suite");
    }


    public static void updateSuite(Suite suite) throws Exception {
        reconnectToMongo();
        mongoOperation.save(suite, "tms_suite");
    }

    public static void removeSuite(String id) throws Exception {
        Suite suite = getSuite(id);
        reconnectToMongo();
        mongoOperation.remove(suite, "tms_suite");
    }


    /**
    * TestCases
    */

    public static TestCase getTestCase(String id) throws Exception {
        reconnectToMongo();
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperation.findOne(query, TestCase.class, "tms_testcase");
    }

    public static void createTestCase(TestCase testCase) throws Exception {
        reconnectToMongo();
        mongoOperation.insert(testCase, "tms_testcase");
    }

    public static void updateTestCase(TestCase testCase) throws Exception {
        reconnectToMongo();
        mongoOperation.save(testCase, "tms_testcase");
    }

    public static void removeTestCase(String id) throws Exception {
        Suite suite = getSuite(id);
        reconnectToMongo();
        mongoOperation.remove(suite, "tms_testcase");
    }


    /**
    * Users
    */
    public static User authoriseUser(User user) throws Exception {
        reconnectToMongo();
        Query query = new Query();

        query.addCriteria(Criteria.where("name").is(user.getName()));

        User dbUser = mongoOperation.findOne(query, User.class, "tms_users");

        if (dbUser == null){
            mongoOperation.insert(user, "tms_users");
            dbUser = user;
        }
        else {
            dbUser.setToken(user.getToken());
            dbUser.setExpire(user.getExpire());
            mongoOperation.save(dbUser, "tms_users");
        }

        return dbUser;
    }

    public static User saveUser(User user) throws Exception {
        if (user.getName() == null || user.getName().equals("")){
            throw new RuntimeException("Username is not defined");
        }
        reconnectToMongo();
        Query query = new Query();

        query.addCriteria(Criteria.where("name").is(user.getName()));
        User dbUser = mongoOperation.findOne(query, User.class, "tms_users");

        if (dbUser == null){
            mongoOperation.insert(user, "tms_users");
            dbUser = user;
        }
        else {
            mongoOperation.save(UserUtils.copyUser(user, dbUser), "tms_users");
        }
        return dbUser;
    }

    public static User getUserByName (String name) throws Exception {
        reconnectToMongo();
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoOperation.findOne(query, User.class, "tms_users");
    }

    public static User getUserByToken (String token) throws Exception {
        reconnectToMongo();
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        return mongoOperation.findOne(query, User.class, "tms_users");
    }

    public static void removeUserSession (String token) throws Exception {
        reconnectToMongo();
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        User user = mongoOperation.findOne(query, User.class, "tms_user");
        if (user != null){
            user.setToken("");
            user.setExpire(0);
            mongoOperation.save(user, "tms_users");
        }
    }


    private static void initMongoOperation() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ConfigUtils configUtils = (ConfigUtils) context.getBean("apiProps");

        SpringMongoConfig.DATABASE_NAME = configUtils.getMongoDbName();
        SpringMongoConfig.HOST = configUtils.getMongoReplicaSet();
        SpringMongoConfig.USERNAME = configUtils.getMongoUserName();
        SpringMongoConfig.PASSWORD = configUtils.getMongoPassword();

        final ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    }


    @Configuration
    public static class SpringMongoConfig extends AbstractMongoConfiguration {

        public static String HOST;
        public static String DATABASE_NAME;
        public static String USERNAME;
        public static String PASSWORD;

        @Override
        public @Bean
        Mongo mongo() throws Exception {
            List<String> hostList = Arrays.asList(HOST.split(","));
            List addrs = new ArrayList();
            for (String host: hostList){
                addrs.add( new ServerAddress( host ) );
            }
            return new Mongo(addrs);
        }

        @Override
        public String getDatabaseName() {
            return DATABASE_NAME;
        }

        @Override
        public UserCredentials getUserCredentials() {
            return new UserCredentials(USERNAME, PASSWORD);
        }

    }

}