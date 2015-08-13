package otherSimpleAlgorithms;


public class Stacks<T extends Object > {
	
	int capacity;
	int size;
	Item<T> top;
	
	Stacks(int cap){
		this.capacity = cap;
	}
	
	public Stacks<Integer> sort(Stacks<Integer> input){
		
		if(input.size <= 0){
			System.out.println("Input stack is empty.");
			return null;
		}
		
		Stacks<Integer> tempStack = new Stacks<Integer>(input.capacity);
		Stacks<Integer> sortedStack = new Stacks<Integer>(input.capacity);
		while(input.size > 0){
			
			Item<Integer> i = input.pop();
			if(sortedStack.size == 0){
				sortedStack.push(i.info);
			}
			else{
				while( !(sortedStack.isEmpty()) && sortedStack.peek().info < i.info ){
					tempStack.push(sortedStack.pop().info);
				}
				sortedStack.push(i.info);
				while(! tempStack.isEmpty()){
					sortedStack.push(tempStack.pop().info);
				}
			}

		}
		
		return sortedStack;
	}
	
	public boolean isEmpty(){
		return (this.size == 0) ? true : false;
	}
	
	public boolean push(T t){
		if(size < capacity){
			Item<T> i = new Item<T>(t);
			i.next = top;
			top = i;
			size++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public Item<T> pop(){
		if(this.top == null){
			return null;
		}
		else{
			Item<T> temp = top;
			top = top.next;
			temp.next = null;
			this.size--;
			return temp;
		}
	}
	
	public Item<T> peek(){
		if(this.top == null){
			return null;
		}
		else{
			return this.top;
		}
	}
	
	public void display(){
		
		Item<T> curItem = this.top;
		int curPtr = 0;
		while(curPtr <= (this.size-1)){
			System.out.print(curItem.info + "\t");
			curItem = curItem.next;
			curPtr++;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		
		
		
		
		// TODO Auto-generated method stub
		Stacks<Integer> stkOfInts = new Stacks<Integer>(10);
		stkOfInts.push(10);
		stkOfInts.push(20);
		stkOfInts.push(15);
		stkOfInts.push(17);
		stkOfInts.push(70);
		stkOfInts.push(15);
		stkOfInts.push(14);
		stkOfInts.push(22);
		stkOfInts.push(25);
		
		stkOfInts.display();
		stkOfInts = stkOfInts.sort(stkOfInts);
		stkOfInts.display();
	}

}

// ==================================================================================== //

class Item <T extends Object >{
	T info;
	Item<T> next;
	
	Item(T i){
		this.info = i;
		this.next = null;
	}
}
