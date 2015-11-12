package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import models.Group;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class LoadGroup implements Callable<Group> {
    
    private String url;

    public LoadGroup(String url) {
        this.url = url;
    }

    @Override
    public Group call() throws Exception {
        Group tempGroup = new Group();
        
        Document doc = Jsoup.connect(url).get();
        Elements authorsEl = doc.select("#authors");
        tempGroup.setAuthors(authorsEl.text());
        Elements classEl = doc.select("#class");
        tempGroup.setClassName(classEl.text());
        Elements groupEl = doc.select("#group");
        tempGroup.setGroupName(groupEl.text());
        
        return tempGroup;
    }
}

public class DataService {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public String getGroups(ArrayList<String> urls) throws InterruptedException, ExecutionException, TimeoutException {
        List<Future<Group>> list = new ArrayList<>();
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        
        for (int i=0; i < urls.size(); i++) {
            Callable<Group> task = new LoadGroup(urls.get(i));
            list.add(executor.submit(task));
            //list.add(executor.schedule(task, 1, TimeUnit.SECONDS));
        }
        
        executor.shutdown();
        
        ArrayList<Group> returnedGroups = new ArrayList();
        for (Future<Group> groupResult : list) {
            try {
                returnedGroups.add(groupResult.get(10, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.out.println("Execution is scheduled for later time.");
            }
            
        }
        
        return gson.toJson(returnedGroups);
    }
    
}