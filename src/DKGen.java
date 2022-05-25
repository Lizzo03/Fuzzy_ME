import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

import java.math.BigInteger;

public class DKGen {
    public static Element[][] dKGen(int d, PARS pars, Element[] S_B, Element[] P_A) {
        Element gamma = pars.getZp().newRandomElement().getImmutable();
        Polynomial f = Utils.newRandomPolynomial(d, pars.getAlpha().duplicate(), pars);
        Polynomial h = Utils.newRandomPolynomial(d, gamma.duplicate(), pars);
        Polynomial q_prime = Utils.newRandomPolynomial(d, pars.getBeta().duplicate(), pars);
        Element G_ID = pars.getG().newRandomElement().duplicate().getImmutable();
        Element[] D_i = new Element[S_B.length];
        Element[] d_i = new Element[S_B.length];
        for (int i = 0; i < S_B.length; i++) {
            Element k_i = pars.getZp().newRandomElement().getImmutable();
            d_i[i] = pars.get_g().duplicate().powZn(k_i).getImmutable();
            D_i[i] = pars.getG2().duplicate().powZn(f.evaluate(S_B[i])).mul(G_ID.powZn(h.evaluate(S_B[i]))).mul(Utils.T(S_B[i], pars).powZn(k_i)).getImmutable();
        }
        Element[] D_i_prime = new Element[P_A.length];
        Element[] d_i_prime = new Element[P_A.length];
        for (int i = 0; i < P_A.length; i++) {
            Element r_i_prime = pars.getZp().newRandomElement().getImmutable();
            d_i_prime[i] = pars.get_g().duplicate().powZn(r_i_prime).getImmutable();
            D_i_prime[i] = pars.getG2().duplicate().powZn(q_prime.evaluate(P_A[i]).mul(BigInteger.valueOf(2))).mul(G_ID.powZn(h.evaluate(P_A[i]).negate())).mul(Utils.H(P_A[i], pars).powZn(r_i_prime)).getImmutable();
        }
        return new Element[][]{D_i, d_i, D_i_prime, d_i_prime};
    }
}
