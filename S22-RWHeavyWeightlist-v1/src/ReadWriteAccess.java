public interface ReadWriteAccess {
    ReadList acquireRead();
    void releaseRead();
    ReadWriteList acquireWrite();
    void releaseWrite(int I);
}
