class MyHashMap {
    class Node{
        int key, value;
        Node next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
            next = null;
        }
    }
    private Node[] slots;
    private int size;
    private static final double load_factor = 0.75;
    public MyHashMap() {
        slots = new Node[10];
        size = 0;
    }
    
    public void put(int key, int value) {
        int index = key % slots.length;
        Node head = slots[index];
        
        while(head != null && head.key != key)
            head = head.next;
        
        if(head != null)
            head.value = value;
        else{
            Node node = new Node(key, value);
            node.next = slots[index];
            slots[index] = node;
            size++;
        }
        
        if(size >= slots.length * load_factor)
            expand();
    }
    
    public int get(int key) {
        int index = key % slots.length;
        Node head = slots[index];
        
        while(head != null && head.key != key)
            head = head.next;
        
        return head == null ? -1:head.value;
    }
    
    public void remove(int key) {
        int index = key % slots.length;
        Node head = slots[index];
        
        Node dummy = new Node(0,0), prev = dummy;
        prev.next = head;
        while(prev.next != null && prev.next.key != key)
            prev = prev.next;
        
        if(prev.next != null && prev.next.key == key){
            prev.next = prev.next.next;
            size--;
        }
        slots[index] = dummy.next;
    }
    
    public void expand(){
        Node[] oldSlots = slots;
        Node[] newSlots = new Node[slots.length * 2];
        slots = newSlots;
        
        for(Node head : oldSlots){
            while(head != null){
                put(head.key, head.value);
                head = head.next;
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */