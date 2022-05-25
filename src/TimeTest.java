import entity.PARS;
import it.unisa.dia.gas.jpbc.Element;

import java.math.BigInteger;
import java.util.List;

import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

public class TimeTest {
    public static void main(String[] args) {
        int timeToTest = 20;
        int n, d;
        PARS pars;
        Element[] S_A, S_B, P_A, P_B, E_i, e_i, D_i, d_i, D_i_prime, d_i_prime, C_1_i, C_2_i, C_3_i, C_4_i, C_5_i;
        Element[][] temp = new Element[0][];
        Element M, C_0, C_1, C_2;
        List<Object> list = null;
        long runTime;
        Timer timer = new Timer();
        timer.setFormat(0, Timer.FORMAT.MICRO_SECOND);

        //d = 10
        d = 10;
        System.out.println("---------------------------- d = " + d + " ----------------------------");
        for (int nNum = 10; nNum <= 60; nNum += 10) {
            n = nNum;
            System.out.println("  ------------------------ [n = " + n + "] ------------------------  ");
            pars = Setup.setup(n);
            S_A = new Element[n];
            S_B = new Element[n];
            P_A = new Element[n];
            P_B = new Element[n];
            for (int i = 0; i < n; i++) {
                S_A[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
                S_B[i] = S_A[i].getImmutable();
                P_A[i] = S_A[i].getImmutable();
                P_B[i] = S_A[i].getImmutable();
            }

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                temp = EKGen.eKGen(d, pars, P_A);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [EKGen] Run Time = " + runTime + " us");

            E_i = temp[0];
            e_i = temp[1];
            System.out.println("  [EKGen] E_i Size = " + getObjectSize(E_i) + " bytes" + ", e_i Size = " + getObjectSize(e_i) + " bytes");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                temp = DKGen.dKGen(d, pars, P_B, P_A);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [DKGen] Run Time = " + runTime + " us");

            D_i = temp[0];
            d_i = temp[1];
            D_i_prime = temp[2];
            d_i_prime = temp[3];
            M = pars.getGT().newRandomElement().getImmutable();
            System.out.println("  [DKGen] D_i Size = " + getObjectSize(D_i) + " bytes" + ", d_i Size = " + getObjectSize(d_i) + " bytes" + ", D_i_prime Size = " + getObjectSize(D_i_prime) + " bytes" + ", d_i_prime Size = " + getObjectSize(d_i_prime) + " bytes");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                list = Enc.enc(d, pars, S_A, P_B, E_i, e_i, M);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [Enc] Run Time = " + runTime + " us");


            C_0 = ((Element) list.get(0)).getImmutable();
            C_1 = ((Element) list.get(1)).getImmutable();
            C_2 = ((Element) list.get(2)).getImmutable();
            C_1_i = (Element[]) list.get(3);
            C_2_i = (Element[]) list.get(4);
            C_3_i = (Element[]) list.get(5);
            C_4_i = (Element[]) list.get(6);
            C_5_i = (Element[]) list.get(7);

            System.out.println("  [Enc] C_0 Size = " + getObjectSize(C_0) + " bytes" + ", C_1 Size = " + getObjectSize(C_1) + " bytes" + ", C_2 Size = " + getObjectSize(C_2) + " bytes" + ", C_1_i Size = " + getObjectSize(C_1_i) + " bytes" + ", C_2_i Size = " + getObjectSize(C_2_i) + " bytes" + ", C_3_i Size = " + getObjectSize(C_3_i) + " bytes" + ", C_4_i Size = " + getObjectSize(C_4_i) + " bytes" + ", C_5_i Size = " + getObjectSize(C_5_i) + " bytes");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                Dec.dec(d, P_A, S_A, P_B, S_B, pars, D_i, d_i, D_i_prime, d_i_prime, C_0, C_1, C_2, C_1_i, C_2_i, C_3_i, C_4_i, C_5_i);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [Dec] Run Time = " + runTime + " us");
        }

        //n = 60
        n = 60;
        pars = Setup.setup(n);
        S_A = new Element[n];
        S_B = new Element[n];
        P_A = new Element[n];
        P_B = new Element[n];
        for (int i = 0; i < n; i++) {
            S_A[i] = pars.getZp().newElement(BigInteger.valueOf(100 + i)).getImmutable();
            S_B[i] = S_A[i].getImmutable();
            P_A[i] = S_A[i].getImmutable();
            P_B[i] = S_A[i].getImmutable();
        }
        System.out.println("---------------------------- n = " + n + " ----------------------------");
        for (int Num = 10; Num <= 50; Num += 10) {
            d = Num;
            System.out.println("  ------------------------ [d = " + d + "] ------------------------  ");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                temp = EKGen.eKGen(d, pars, P_A);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [EKGen] Run Time = " + runTime + " us");

            E_i = temp[0];
            e_i = temp[1];
            System.out.println("  [EKGen] E_i Size = " + getObjectSize(E_i) + " bytes" + ", e_i Size = " + getObjectSize(e_i) + " bytes");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                temp = DKGen.dKGen(d, pars, P_B, P_A);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [DKGen] Run Time = " + runTime + " us");

            D_i = temp[0];
            d_i = temp[1];
            D_i_prime = temp[2];
            d_i_prime = temp[3];
            M = pars.getGT().newRandomElement().getImmutable();
            System.out.println("  [DKGen] D_i Size = " + getObjectSize(D_i) + " bytes" + ", d_i Size = " + getObjectSize(d_i) + " bytes" + ", D_i_prime Size = " + getObjectSize(D_i_prime) + " bytes" + ", d_i_prime Size = " + getObjectSize(d_i_prime) + " bytes");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                list = Enc.enc(d, pars, S_A, P_B, E_i, e_i, M);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [Enc] Run Time = " + runTime + " us");

            C_0 = ((Element) list.get(0)).getImmutable();
            C_1 = ((Element) list.get(1)).getImmutable();
            C_2 = ((Element) list.get(2)).getImmutable();
            C_1_i = (Element[]) list.get(3);
            C_2_i = (Element[]) list.get(4);
            C_3_i = (Element[]) list.get(5);
            C_4_i = (Element[]) list.get(6);
            C_5_i = (Element[]) list.get(7);

            System.out.println("  [Enc] C_0 Size = " + getObjectSize(C_0) + " bytes" + ", C_1 Size = " + getObjectSize(C_1) + " bytes" + ", C_2 Size = " + getObjectSize(C_2) + " bytes" + ", C_1_i Size = " + getObjectSize(C_1_i) + " bytes" + ", C_2_i Size = " + getObjectSize(C_2_i) + " bytes" + ", C_3_i Size = " + getObjectSize(C_3_i) + " bytes" + ", C_4_i Size = " + getObjectSize(C_4_i) + " bytes" + ", C_5_i Size = " + getObjectSize(C_5_i) + " bytes");

            runTime = 0;
            for (int i = 0; i < timeToTest; i++) {
                timer.start(0);
                Dec.dec(d, P_A, S_A, P_B, S_B, pars, D_i, d_i, D_i_prime, d_i_prime, C_0, C_1, C_2, C_1_i, C_2_i, C_3_i, C_4_i, C_5_i);
                runTime += timer.stop(0);
            }
            runTime = runTime / timeToTest;
            System.out.println("  [Dec] Run Time = " + runTime + " us");
        }
    }
}
