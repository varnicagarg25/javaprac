import java.util.*;

class Twitter {
    private static int timestamp = 0;

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timestamp++;
            this.next = null;
        }
    }

    private static class User {
        int id;
        Set<Integer> followed;
        Tweet head;

        User(int id) {
            this.id = id;
            this.followed = new HashSet<>();
            follow(id); // User follows themselves
            this.head = null;
        }

        void follow(int id) {
            followed.add(id);
        }

        void unfollow(int id) {
            if (id != this.id) {
                followed.remove(id);
            }
        }

        void post(int id) {
            Tweet newTweet = new Tweet(id);
            newTweet.next = head;
            head = newTweet;
        }
    }

    private Map<Integer, User> userMap;

    public Twitter() {
        userMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        userMap.putIfAbsent(userId, new User(userId));
        userMap.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;

        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

        // Put the head of the tweet list for each followed user into the heap
        for (int user : users) {
            User u = userMap.get(user);
            if (u != null && u.head != null) {
                maxHeap.add(u.head);
            }
        }

        // Merge k sorted lists to extract the top 10 most recent tweets
        while (!maxHeap.isEmpty() && res.size() < 10) {
            Tweet t = maxHeap.poll();
            res.add(t.id);
            if (t.next != null) {
                maxHeap.add(t.next);
            }
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        userMap.putIfAbsent(followerId, new User(followerId));
        userMap.putIfAbsent(followeeId, new User(followeeId));
        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId) return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

