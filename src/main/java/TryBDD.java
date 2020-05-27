import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

public class TryBDD {

    public static void main(String[] args){
        BDDFactory bdd = BDDFactory.init(4,10);
        bdd.one();
    }

}
