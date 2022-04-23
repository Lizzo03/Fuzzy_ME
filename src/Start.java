import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

import java.math.BigInteger;
import java.util.List;

public class Start {
    public static void main(String[] args){
        int n = 10;
        int d = 10;
        PARS pars = Setup.setup(n);
        Element[] S_A = new Element[n];
        for (int i = 0; i < S_A.length; i++) {
            S_A[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[] S_B = new Element[n];
        for (int i = 0; i < S_B.length; i++) {
            S_B[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[] P_A = new Element[n];
        for (int i = 0; i < P_A.length; i++) {
            P_A[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[] P_B = new Element[n];
        for (int i = 0; i < P_B.length; i++) {
            P_B[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
        }
        Element[][] temp = EKGen.eKGen(d, pars, P_A);
        Element[] E_i = temp[0];
        Element[] e_i = temp[1];
        temp = DKGen.dKGen(d, pars, P_B, P_A);
        Element[] D_i = temp[0];
        Element[] d_i = temp[1];
        Element[] D_i_prime = temp[2];
        Element[] d_i_prime = temp[3];
        Element M = pars.getGT().newRandomElement().getImmutable();
        List<Object> list = Enc.enc(d, pars, S_A, P_B, E_i, e_i, M);
        Element C = ((Element) list.get(0)).getImmutable();
        Element C_prime = ((Element) list.get(1)).getImmutable();
        Element[] C_i = (Element[]) list.get(2);
        Element[] C_i_prime = (Element[]) list.get(3);
        Element M_prime = Dec.dec(d, P_A, S_A, P_B, S_B, pars, D_i, d_i, C_prime, C_i, D_i_prime, d_i_prime, C_i_prime, C);
        System.out.println("M = " + M);
        System.out.println("M_prime = " + M_prime);
        System.out.println("Result = " + M.equals(M_prime));
    }
}
