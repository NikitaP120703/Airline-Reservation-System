package Airline;
import java.util.*;
class passenger_details
{
	passenger_details next=null;
	String name,address,gender;
	long ph_no;
	int age,u_id;
	passenger_details(String name1,String address1,int age1,String gender1,long ph_no1,int u_id1)
	{	
		name=name1;
		address=address1;
		age=age1;
		gender=gender1;
		ph_no=ph_no1;
		u_id=u_id1;
	}
}

class Queue
{
	int front, rear;
	int maxsize=10;
	int passenger[];
	int flight[];
	Queue()
	{
		passenger=new int[10];
		flight=new int[10];
	}
	boolean isFull()
	{
		if (rear==maxsize)
			return true;
		else
			return false;
	}
	boolean isEmpty()
	{
		if(front==rear)
			return true;
		else
			return false;
	}
	void enqueue(int userid,int n)
	{
		if (isFull())
		{
			System.out.println("Waiting List is Full");
			return;
		}
		rear = rear + 1;
		passenger[rear]=userid;
		flight[rear]=n;
	}
	int dequeue(int f)
	{
		if(isEmpty())
		{
			System.out.println("No passengers in waiting");
			return 0;
		}
		for(int i=rear;i<10;i++)
		{
			if(f==flight[rear])
			{
				return passenger[rear];
			}
			rear--;
		}
		return 0;		
	}

}




public class Main {
	Queue object = new Queue();
	int seat[]=new int[6];
	static Scanner sc=new Scanner(System.in);
	String date,place,seat_type,name,address,gender,time,mode;
	long ph_no;
	int choice,u_id,age,seat_num,flightnum,f,s;
	double price,gstprice;
	passenger_details head,temp;
	static Main obj[]=new Main[6];
	Main()
	{
		for(int i=0;i<6;i++)
		{
			seat[i]=0;
		}


	}
	public static void main(String Args[])
	{
		Main ob = new Main();
		for(int i=0;i<6;i++)
		{
			obj[i]=new Main();
		}
		ob.methodcall();

	}
	void methodcall()
	{
		System.out.println("Welcome to Airline Reservation System");
		System.out.println("1.Book a ticket");
		System.out.println("2.Cancel a ticket");
		System.out.println("0.Exit");
		choice = sc.nextInt();
		if(choice==1)
		{
			details();	
			int k=flight();
			isFull(seat_type,k);
			seat_display(k);
			payment();
			ticket();
			System.out.println("Your booking is confirmed");
		}
		else if(choice==2)
		{
			System.out.println("Enter your flight number");
			f=sc.nextInt();
			System.out.println("Enter your seat number");
			s=sc.nextInt();
			obj[f].seat[s-1]=0;
			System.out.println("Your booking is cancelled");
			if(!object.isEmpty())
			{
				object.dequeue(f);
				int k=flight();
				isFull(seat_type,k);
				seat_display(k);
				payment();
				ticket();
			}
		}
		else if(choice==0)
		{
			System.exit(0);
		}
		methodcall();
	}


	void details()
	{
		passenger_details temp;
		System.out.println("1.Existing User\n2.New User");
		choice = sc.nextInt();
		if(choice==1)
		{
			System.out.println("Enter User Id");
			int key_uid=sc.nextInt();
			temp=head;
			while(temp.u_id!=key_uid && temp!=null)
			{
				temp=temp.next;
			}
			name=temp.name;
			address=temp.address;
			age=temp.age;
			gender=temp.gender;
			ph_no=temp.ph_no;
			u_id=temp.u_id;
		}
		if(choice==2)
		{
			System.out.println("Enter name of passenger");
			name=sc.next();
			System.out.println("Enter address of passenger");
			address=sc.next();
			System.out.println("Enter age of passenger");
			age=sc.nextInt();
			System.out.println("Enter gender of passenger");
			gender=sc.next();
			System.out.println("Enter phone number of passenger");
			ph_no=sc.nextLong();
			System.out.println("Enter UID of passenger");
			u_id=sc.nextInt();

			passenger_details new_passenger = new passenger_details(name,address,age,gender,ph_no,u_id);
			if(head==null)
			{
				head=new_passenger;
				temp=head;
			}
			else
			{
				temp=head;
				while(temp.next!=null)
				{
					temp=temp.next;
				}
				temp.next=new_passenger;
			}
		}
		System.out.println("Select your seat type \n1.Business Class\n2.Economy Class");
		choice = sc.nextInt();
		if(choice==1)
		{
			seat_type="Business Class";
		}
		if(choice==2)
		{
			seat_type="Economy Class";
		}


	}
	void isFull(String seattype,int n)
	{
		if(seattype.equals("Business Class"))
		{
			if(obj[n].seat[0]==1&&obj[n].seat[1]==1&&obj[n].seat[2]==1)
			{
				System.out.println("Business Class is full");

				if(obj[n].seat[3]==0||obj[n].seat[4]==0||obj[n].seat[5]==0)
				{
					System.out.println("Do you want to book an economy class Y/N?");
					char ch=sc.next().charAt(0);
					if(ch=='Y')
					{
						seat_type="Economy Class";
						return;
					}
				}
				else
				{
					System.out.println("Flight is full\nYou are added to waiting queue");
					waiting(u_id,n);
				}



			}
		}
		if(seattype.equals("Economy Class"))
		{
			if(obj[n].seat[3]==1&&obj[n].seat[4]==1&&obj[n].seat[5]==1)
			{
				System.out.println("Economy Class is full");

				if(obj[n].seat[0]==0||obj[n].seat[1]==0||obj[n].seat[2]==0)
				{
					System.out.println("Do you want to book an business class Y/N?");
					char ch=sc.next().charAt(0);
					if(ch=='Y')
					{
						seat_type="Business Class";
						return;
					}
				}
				else
				{
					System.out.println("Flight is full\nYou are added to waiting queue");
					waiting(u_id,n);
				}



			}

		}

	}
	int flight()
	{


		System.out.println("Select your date \n1.14/12/2022\n2.15/12/2022");
		choice=sc.nextInt();
		if(choice==1)
		{
			date="14/12/2022";
			System.out.println("Select your flight : \n1.Pune to Bangalore \n2.Pune to Delhi \n3.Pune to Mumbai");
			choice=sc.nextInt();
			if(choice==1)
			{
				place="Pune to Bangalore";
				flightnum=0;
				return 0;
			}
			if(choice==2)
			{
				place="Pune to Delhi";
				flightnum=1;
				return 1;
			}
			if(choice==3)
			{
				place="Pune to Mumbai";
				flightnum=2;
				return 2;
			}
		}

		if(choice==2)
		{
			date="15/12/2022";
			System.out.println("Select your flight : \n1.Pune to Bangalore \n2.Pune to Delhi \n3.Pune to Mumbai");
			choice=sc.nextInt();
			if(choice==1)
			{
				place="Pune to Bangalore";
				flightnum=3;
				return 3;
			}
			if(choice==2)
			{
				place="Pune to Delhi";
				flightnum=4;
				return 4;
			}
			if(choice==3)
			{
				place="Pune to Mumbai";
				flightnum=5;
				return 5;
			}
		}
		return 0;
	}
	void seat_display(int n)
	{
		System.out.println("SEAT 1-3 for business class\nSeat 4-6 for economy class");
		for(int i=0;i<3;i++)
		{
			System.out.print((i+1)+" ");
		}
		System.out.println();
		for(int j=3;j<6;j++)
		{
			System.out.print((j+1)+" ");
		}
		System.out.println();
		for(int i=0;i<3;i++)
		{
			if(obj[n].seat[i]==0)
			{
				System.out.print("E ");
			}
			else
			{
				System.out.print("F ");
			}
		}
		System.out.println();
		for(int j=3;j<6;j++)
		{
			if(obj[n].seat[j]==0)
			{
				System.out.print("E ");
			}
			else
			{
				System.out.print("F ");
			}

		}
		System.out.println();

		System.out.println("Select seat number");
		seat_num=sc.nextInt();
		obj[n].seat[seat_num-1]=1;
	}
	void payment()
	{
		if(seat_type.equals("Business Class"))
		{
			if(place.equals("Pune to Bangalore"))
			{
				price=6000;
			}
			if(place.equals("Pune to Delhi"))
			{
				price=9000;
			}
			if(place.equals("Pune to Mumbai"))
			{
				price=2500;
			}	
		}
		if(seat_type.equals("Economy Class"))
		{
			if(place.equals("Pune to Bangalore"))
			{
				price=3000;
			}
			if(place.equals("Pune to Delhi"))
			{
				price=5000;
			}
			if(place.equals("Pune to Mumbai"))
			{
				price=1800;
			}	
		}
		if(age<=21)
		{
			price=price-0.1*price;
		}
		if(age>55)
		{
			price=price-0.2*price;
		}
		gstprice = 0.18*price;
		System.out.println("Enter your mode of payment : \n1.Cash\n2.UPI\n3.Debit Card/Credit Card\n4.Net Banking");
		choice=sc.nextInt();
		if(choice==1)
			mode="Cash";
		if(choice==2)
			mode="UPI";
		if(choice==3)
			mode="Debit Card/Credit Card";
		if(choice==4)
			mode="Net Banking";
	}
	void waiting(int uid,int fn)
	{
		object.enqueue(uid,fn);
		methodcall();
	}
	void ticket()
	{
		System.out.println("*************************TICKET*************************");
		System.out.println("Name of the passenger                      : "+name);
		System.out.println("Address of the passenger                   : "+address);
		System.out.println("Age of the passenger                       : "+age);
		if(age<=21) {
			System.out.println("Student offer applied!!!!!!!!!!\nFLAT 10%OFF");
		}
		if(age>55) {
			System.out.println("Senior citizen offer applied!!!!!!!!!!\nFLAT 20%OFF");
		}
		System.out.println("Gender of the passenger                    : "+gender);
		System.out.println("Phone number of the passenger              : "+ph_no);
		System.out.println("UID of the passenger                       : "+u_id);
		System.out.println("Flight                                     : "+place);
		System.out.println("Flight number                              : "+flightnum);
		System.out.println("Date of flight                             : "+date);
		System.out.println("Seat type                                  : "+seat_type);
		System.out.println("Seat number                                : "+seat_num);
		System.out.println("Ticket price                               : "+price);
		System.out.println("Taxes and convenience fee                  : "+gstprice);
		System.out.println("Total amount                               : "+(price+gstprice));
		System.out.println("Mode of payment                            : "+mode);
	}


}
