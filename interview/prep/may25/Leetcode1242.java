/*
 * 1242. Web Crawler Multithreaded
 * Given a URL startUrl and an interface HtmlParser, 
 * implement a Multi-threaded web crawler to crawl all links that are under the same hostname as startUrl.
 * Return all URLs obtained by your web crawler in any order.
 * Your crawler should:
   ** Start from the page: startUrl
   ** Call HtmlParser.getUrls(url) to get all URLs from a webpage of a given URL.
   ** Do not crawl the same link twice.
   ** Explore only the links that are under the same hostname as startUrl.
 * As shown in the example URL above, the hostname is example.org. 
 * For simplicity's sake, you may assume all URLs use HTTP protocol without any port specified. 
 * For example, the URLs http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, 
 * while URLs http://example.org/test and http://example.com/abc are not under the same hostname.
 * The HtmlParser interface is defined as such:
        interface HtmlParser {
          // Return a list of all urls from a webpage of given url.
          // This is a blocking call, that means it will do HTTP request and return when this request is finished.
          public List<String> getUrls(String url);
        }
 * Note that getUrls(String url) simulates performing an HTTP request. 
 * You can treat it as a blocking function call that waits for an HTTP request to finish. 
 * It is guaranteed that getUrls(String url) will return the URLs within 15ms. 
 * Single-threaded solutions will exceed the time limit so, can your multi-threaded web crawler do better?
 * Below is an example explaining the functionality of the problem. 
 * For custom testing purposes, you'll have three variables urls, edges and startUrl.
 * Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.
 * 
 * Example 
  ** Input:
  urls = [
    "http://news.yahoo.com",
    "http://news.yahoo.com/news",
    "http://news.yahoo.com/news/topics/",
    "http://news.google.com",
    "http://news.yahoo.com/us"
  ]
    ** edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
    ** startUrl = "http://news.yahoo.com/news/topics/"
    ** Output: [
    "http://news.yahoo.com",
    "http://news.yahoo.com/news",
    "http://news.yahoo.com/news/topics/",
    "http://news.yahoo.com/us"
  ]
 * 
 * Constraints:
   ** 1 <= urls.length <= 1000
   ** 1 <= urls[i].length <= 300
   ** startUrl is one of the urls.
   ** Hostname label must be from 1 to 63 characters long, including the dots, may contain only the ASCII letters from 'a' to 'z', digits from '0' to '9' and the hyphen-minus character ('-').
   ** The hostname may not start or end with the hyphen-minus character ('-'). 
   ** See:  https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
   ** You may assume there're no duplicates in the URL library.
 */
/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
import  java.net.*;

class Leetcode1242 {
    
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = ConcurrentHashMap.newKeySet(); // Thread-safe set
        visited.add(startUrl);

        ExecutorService executor = Executors.newFixedThreadPool(16); // Choose a reasonable thread pool size
        AtomicInteger activeTasks = new AtomicInteger(1); // Start with 1 active task
        CountDownLatch doneSignal = new CountDownLatch(1);

        try {
            String hostname = new URL(startUrl).getHost();
            executor.execute(new CrawlTask(startUrl, hostname, htmlParser, visited, executor, activeTasks, doneSignal));
            doneSignal.await(); // Wait for all crawling to complete
        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return new ArrayList<>(visited);
    }
    
    class CrawlTask implements Runnable {
        private final String url;
        private final String hostname;
        private final HtmlParser parser;
        private final Set<String> visited;
        private final ExecutorService executor;
        private final AtomicInteger activeTasks;
        private final CountDownLatch doneSignal;

        public CrawlTask(String url, String hostname, HtmlParser parser, Set<String> visited,
                         ExecutorService executor, AtomicInteger activeTasks, CountDownLatch doneSignal) {
            this.url = url;
            this.hostname = hostname;
            this.parser = parser;
            this.visited = visited;
            this.executor = executor;
            this.activeTasks = activeTasks;
            this.doneSignal = doneSignal;
        }
        
        @Override
        public void run() {
            try {
                for (String nextUrl : parser.getUrls(url)) {
                    try {
                        String nextHost = new URL(nextUrl).getHost();
                        if (nextHost.equals(hostname) && visited.add(nextUrl)) {
                            activeTasks.incrementAndGet();
                            executor.execute(new CrawlTask(nextUrl, hostname, parser, visited, executor, activeTasks, doneSignal));
                        }
                    } catch (MalformedURLException e) {
                        // Skip invalid URL
                    }
                }
            } finally {
                if (activeTasks.decrementAndGet() == 0) {
                    doneSignal.countDown(); // All tasks finished
                }
            }
        }
    }

    
    public List<String> crawlFJPool(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = ConcurrentHashMap.newKeySet(); //thread-safe set         
        visited.add(startUrl); 
        
        try{
            String hostname = new URL(startUrl).getHost(); 
            
            ForkJoinPool.commonPool().invoke(new CrawlTask1(visited, startUrl, hostname, htmlParser)); 
            
            return new ArrayList<String>(visited); 
            
        } catch(MalformedURLException e) {
        
            System.err.println("Invalid URL: " + e.getMessage());
            
        }
        
        return new ArrayList<String>(); 
    }
    
    class CrawlTask1 extends RecursiveAction { //task for fork join pool 
        Set<String> visited; 
        String url; 
        String hostname; 
        HtmlParser parser; 
        
        public CrawlTask1(Set<String> visited, String url, String hostname, HtmlParser parser) {
            this.visited = visited; 
            this.url = url; 
            this.hostname = hostname; 
            this.parser = parser; 
        }
        
        @Override
        protected void	compute() {
            List<RecursiveAction> tasks = new ArrayList<>(); 
            
            for(String nextUrl : parser.getUrls(url)) {
                try {
                    if(new URL(nextUrl).getHost().equals(hostname) && visited.add(nextUrl)) {
                        CrawlTask1 task = new CrawlTask1(visited, nextUrl, hostname, parser); 
                        task.fork(); 
                        tasks.add(task); 
                    }
                } catch(MalformedURLException e) {
                    //skip it         
                 } 
            }
            
            for(RecursiveAction task : tasks) {
                task.join(); 
            }
        }
    }
    
    public List<String> crawlSingleThreaded(String startUrl, HtmlParser htmlParser) {
        try {
        Set<String> result = new HashSet<>(); 
        
        
        URL s_url = new URL(startUrl); 
        String hostname = s_url.getHost(); 
        
        Set<String> visited = new HashSet<>(); 
        
        Queue<String> q = new LinkedList<>(); 
        q.offer(startUrl);
        visited.add(startUrl); 
        
        while(!q.isEmpty()) {
            String urlString = q.poll(); 
            result.add(urlString); 
                       
            List<String> related = htmlParser.getUrls(urlString); 
            for(String r : related) {
                URL r_url = new URL(r); 
                String r_hostname = r_url.getHost();                 
                if(r_hostname.equals(hostname) && visited.add(r)) {
                    q.offer(r);                     
                }
            }            
        }
        
        
        return new ArrayList<String>(result); 
        } catch (MalformedURLException e) {
             System.err.println("Invalid URL: " + e.getMessage());
        }
        return new ArrayList<String>(); 
    }
}
