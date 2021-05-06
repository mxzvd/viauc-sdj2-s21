public interface TreasureRoomDoor {
    TreasureRead acquireReadAccess();
    void releaseReadAccess();
    TreasureReadWrite acquireWriteAccess();
    void releaseWriteAccess();
}
