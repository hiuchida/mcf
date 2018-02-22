package gr.unirico.mcflib.impl;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.util.DigestUtil;
import gr.unirico.mcflib.util.StringUtil;

public class ProofOfWork {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static int calc(int lastProof, String prevhash) {
		int proof = 0;
		if (lastProof >= 0) {
			while (!validate(lastProof, prevhash, proof)) {
				proof++;
			}
		}
		logger.info("calc: {}, {} -> {}", lastProof, prevhash, proof);
		return proof;
	}

	public static String calcHash(int lastProof, String prevhash, int proof) {
		if (lastProof >= 0) {
			String guess = String.valueOf(lastProof) + prevhash + String.valueOf(proof);
			String hash = DigestUtil.calcHex(guess);
			return proof + " -> " + StringUtil.getShortHash(hash);
		} else {
			return "" + proof;
		}
	}
	
	public static boolean validate(int lastProof, String prevhash, int proof) {
		if (lastProof >= 0) {
			String guess = String.valueOf(lastProof) + prevhash + String.valueOf(proof);
			String hash = DigestUtil.calcHex(guess);
			return hash.startsWith("0000");
		} else {
			return proof == 0;
		}
	}

}
