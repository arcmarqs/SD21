public class PeerNode {
    Peer peer;
    Int peerID;
    Boolean lock;
    Peer nextPeer;

public PeerNode(Peer peer, Int id,Peer nextPeer) {
    this.peer = peer;
    this.id = id;
    this.nextPeer = nextPeer;
}

public void setLock(Boolean state) {
    lock = state;
}

public Boolean getLock() {
    return this.lock;
}

public Peer getNextPeer() {
    return this.nextPeer;
}



}