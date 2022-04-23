import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

import java.util.ArrayList;
import java.util.List;

public class Enc {
    public static List<Object> enc(int d, PARS pars, Element[] S_A, Element[] P_B, Element[] E_i, Element[] e_i, Element M){
        Element s = pars.getZp().newRandomElement().getImmutable();
        Element K_s = pars.getPairing().pairing(pars.getG1().duplicate(), pars.getG2().duplicate()).powZn(s).getImmutable();
        Element C_prime = pars.get_g().duplicate().powZn(s).getImmutable();
        Element[] C_i = new Element[P_B.length];
        for (int i = 0; i < P_B.length; i++) {
            C_i[i] = Utils.T(P_B[i], pars).powZn(s).getImmutable();
        }
        Element[] C_i_prime = new Element[S_A.length];
        for (int i = 0; i < S_A.length; i++) {
            C_i_prime[i] = Utils.H(S_A[i], pars).powZn(s).getImmutable();
        }
        Element[] S_A_prime = new Element[d];
        System.arraycopy(S_A, 0, S_A_prime, 0, S_A_prime.length);
        Element K_l = pars.getGT().newOneElement().getImmutable();
        for (int i = 0; i < S_A_prime.length; i++) {
            K_l = K_l.mul(pars.getPairing().pairing(E_i[i], pars.get_g().duplicate().powZn(s)).div(pars.getPairing().pairing(e_i[i], Utils.H(S_A_prime[i].duplicate(), pars).powZn(s))).powZn(Utils.delta(pars.getZp().newZeroElement(), S_A_prime[i].duplicate(), S_A_prime, pars))).getImmutable();
        }
        Element C =  M.duplicate().mul(K_s.duplicate()).mul(K_l.duplicate()).getImmutable();
        List<Object> result = new ArrayList<>();
        result.add(C);
        result.add(C_prime);
        result.add(C_i);
        result.add(C_i_prime);
        return result;
    }
}
