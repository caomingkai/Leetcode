class Logger {

    /*
    
    HashMap<String, Integer> cache = new HashMap<>();
    public Logger() {
        
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        if( cache.containsKey(message) ){
            int t = cache.get(message);
            if( timestamp-t < 10 )
                return false;
            else{
                cache.put( message, timestamp );
                return true;
            }
        }
        cache.put( message, timestamp );
        return true;
    }
    */
    class Log {
        int timestamp;
        String message;
        public Log(int aTimestamp, String aMessage) {
            timestamp = aTimestamp;
            message = aMessage;
        }
    }

    PriorityQueue<Log> recentLogs;
    Set<String> recentMessages;   

        /** Initialize your data structure here. */
    public Logger() {
        recentLogs = new PriorityQueue<Log>(10, new Comparator<Log>() {
            public int compare(Log l1, Log l2) {
                return l1.timestamp - l2.timestamp;
            }
        });
        recentMessages = new HashSet<String>();
    }


    public boolean shouldPrintMessage(int timestamp, String message) {
        while (recentLogs.size() > 0)   {
            Log log = recentLogs.peek();
            // discard the logs older than 10 minutes
            if (timestamp - log.timestamp >= 10) {
                recentLogs.poll();
                recentMessages.remove(log.message);
            } else 
                break;
        }
        boolean res = !recentMessages.contains(message);
        if (res) {
            recentLogs.add(new Log(timestamp, message));
            recentMessages.add(message);
        }
        return res;
    }

}
