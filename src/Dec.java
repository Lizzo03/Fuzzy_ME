import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

public class Dec {
    public static Element dec(int d, Element[] P_A, Element[] S_A, Element[] P_B, Element[] S_B, PARS pars, Element[] D_i, Element[] d_i, Element C_prime, Element[] C_i, Element[] D_i_prime, Element[] d_i_prime, Element[] C_i_prime, Element C){
        Element[] W_A_prime = Utils.intersect(P_A, S_A);
        Element[] W_B_prime = Utils.intersect(P_B, S_B);
        if (W_A_prime.length >= d && W_B_prime.length >= d){
            Element[] W_A = new Element[d];
            System.arraycopy(W_A_prime, 0, W_A, 0, W_A.length);
            Element[] W_B = new Element[d];
            System.arraycopy(W_B_prime, 0, W_B, 0, W_B.length);
            Element K_s_prime = pars.getGT().newOneElement().getImmutable();
            for (int i = 0; i < W_B.length; i++) {
                K_s_prime = K_s_prime.mul(pars.getPairing().pairing(D_i[i].duplicate(), C_prime.duplicate()).div(pars.getPairing().pairing(d_i[i].duplicate(), C_i[i].duplicate())).powZn(Utils.delta(pars.getZp().newZeroElement(), W_B[i], W_B, pars))).getImmutable();
            }
            Element K_l_prime = pars.getGT().newOneElement().getImmutable();
            for (int i = 0; i < W_B.length; i++) {
                K_l_prime = K_l_prime.mul(pars.getPairing().pairing(D_i_prime[i].duplicate(), C_prime.duplicate()).div(pars.getPairing().pairing(d_i_prime[i].duplicate(), C_i_prime[i].duplicate())).powZn(Utils.delta(pars.getZp().newZeroElement(), W_A[i], W_A, pars))).getImmutable();
            }
            Element M = C.duplicate().div(K_s_prime.duplicate().mul(K_l_prime.duplicate())).getImmutable();
            return M;
        } else
            return null;
    }
}
