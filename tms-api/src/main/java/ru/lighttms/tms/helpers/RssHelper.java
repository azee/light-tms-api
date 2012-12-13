package ru.lighttms.tms.helpers;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 6/20/12
 * Time: 4:23 PM
  */

public class RssHelper {

    public static SyndEntry makeEntry(String author, String title, String content, String contentType, String url,
            Date publishedDate, SyndCategory... categories)
    {
        SyndEntry entry = new SyndEntryImpl();
        entry.setAuthor(author);
        entry.setTitle(title);
        entry.setLink(url);
        entry.setUri(url);
        entry.setPublishedDate(publishedDate);
        entry.setCategories(Arrays.asList(categories));


        SyndContent description = new SyndContentImpl();
        description.setType(contentType);
        description.setValue(content);

        entry.setDescription(description);
        return entry;
    }


    public static SyndFeed makeFeed(String type, String author, String title, String description, String url, List<SyndEntry> entries)
    {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(type);
        feed.setAuthor(author);
        feed.setCopyright(author);
        feed.setTitle(title);
        feed.setDescription(description);
        feed.setLink(url);
        feed.setUri(url);
        feed.setEntries(entries);
        return feed;
    }


    public static SyndCategory makeCategory(String name, String taxonomyUrl)
    {
        SyndCategory category = new SyndCategoryImpl();
        category.setName(name);
        category.setTaxonomyUri(taxonomyUrl);
        return category;
    }

}
