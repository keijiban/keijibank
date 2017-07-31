package 課題11;
import mylib.Util;
import 課題2.Card;
import 課題4.CardDeck;
import 課題6.Player;
import 課題7.Dealer;
public class BlackJack {
	private CardDeck deck;
	private Dealer dealer;
	private Player player;
	
	public BlackJack(String dname,String pname){
		deck=new CardDeck(CARDS);
		dealer=new Dealer(dname,DEALER_ICON,deck);
		player=new Player(pname,PLAYER_ICON,deck);
	}
	public void initialize(){
		if(deck.size()<LIMIT){
			deck.intialize();
		}
		dealer.initialize();
		player.initialize();
	}
	public void show(){
		System.out.println();
		System.out.println(dealer);
		System.out.println(player);
	}
	public void play(){
		String str;
		do{
			System.out.println(BANNER);
			deck.intialize();
			dealer.initialize();
			player.initialize();
			deal();
			dealer.over16();
			animation(TIMES, DELAY, ANIMATION_ICON);
			show();
			judge();
			System.out.println(Q_CONTINUE);
		}while((str=Util.getString("続けますか"))==null);
	}
	static final String DEALER_ICON="□";
	static final String PLAYER_ICON="■";
	static final String BANNER="\n□■□■□■BlackJack□■□■□■";
	static final int CARDS=52;
	static final String Q_CONTINUE
					   ="続けますか(Enter/n)";
	static final int LIMIT = 17;
	
	public static void main(String[] args){
		BlackJack   bj = new BlackJack("dealer","player");
		bj.play();
	}
	public void showHalf(){
		System.out.println();
		System.out.println(dealer.half());
		System.out.println(player);
	}
	public void deal(){
		while(player.score()<21){
			String str=Util.getString(Q_DEAL);
			if(str!=null)
				break;
			player.addACard();
			showHalf();
		}
		dealer.over16();
	}
	public void judge(){
		int d = dealer.score();
		int p = player.score();
		if(d>21&&p>21||d==p)
			System.out.println("引き分け");
		else if(p<=21&&(d>21||d<p))
			System.out.println("あなたの勝ち");
		else
			System.out.println("ディーラーの勝ち");
	}
	public void animation(int times,int delay,String icon){
		System.out.println();
		try{
			for(int i=0;i<times;i++){
				System.out.println(icon);
				Thread.sleep(delay);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	static final String Q_DEAL
	="さらにカードを引きますか(Enter/n)";
	static final String EVEN="引き分け";
	static final String WIN="あなたの勝ち";
	static final String LOST="ディーラーの勝ち";
	
	static final String ANIMATION_ICON="□■";
	static final int    TIMES =25;
	static final int    DELAY=20;
}
