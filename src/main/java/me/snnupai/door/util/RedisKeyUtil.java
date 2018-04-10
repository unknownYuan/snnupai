package me.snnupai.door.util;


import me.snnupai.door.model.EntityType;

public class RedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";
    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";

    private static String BIZ_FANS = "FANS";
    private static String BIZ_FOLLOW_ENTITY = "FOLLOW_ENTITY";
    private static String BIZ_TIMELINE = "TIMELINE";
    private static String COLLECTION = "COLLECTION";
    /**
     *  关注者类型是唯一的，只有用户一种类型
     * @param id
     * @return
     */
    public static String getFansKey(long id) {
        return BIZ_FANS + SPLIT + id;
    }

    /**
     *  被关注者类型有很多
     * @param entityType
     * @param id
     * @return
     */
    public static String getFollowEntityKey(int entityType, String id){
        return BIZ_FOLLOW_ENTITY + SPLIT + entityType + SPLIT + id;
    }

    public static String getLikeKey(int entityType, Long entityId) {
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getDisLikeKey(int entityType, Long entityId) {
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getEventQueueKey() {
        return BIZ_EVENTQUEUE;
    }

//    // 某个实体的粉丝key
//    public static String getFollowerKey(int entityType, int entityId) {
//        return BIZ_FOLLOWER + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
//    }
//
//    // 每个用户对某类实体的关注key
//    public static String getFolloweeKey(int userId, int entityType) {
//        return BIZ_FOLLOWEE + SPLIT + String.valueOf(userId) + SPLIT + String.valueOf(entityType);
//    }


    public static String getTimelineKey(int userId) {
        return BIZ_TIMELINE + SPLIT + String.valueOf(userId);
    }

    public static String getCollectionKey(int entityType, Long userId) {
        return COLLECTION + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(userId);
    }
}
