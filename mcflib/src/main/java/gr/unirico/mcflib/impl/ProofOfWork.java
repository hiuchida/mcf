package gr.unirico.mcflib.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.util.DigestUtil;
import gr.unirico.mcflib.util.StringUtil;

public class ProofOfWork {
	private static Logger logger = LoggerFactory.getLogger(ProofOfWork.class);

	public static int calc(int lastProof, String prevhash) {
		int proof = 0;
		if (lastProof >= 0) {
			while (!validatePrivate(lastProof, prevhash, proof)) {
				proof++;
			}
		}
		logger.info("calc: {}, {} -> {}", lastProof, prevhash, proof);
		return proof;
	}

	public static boolean validate(int lastProof, String prevhash, int proof) {
		boolean rc = validatePrivate(lastProof, prevhash, proof);
		logger.info("validate: {}, {}, {} -> {}", lastProof, prevhash, proof, rc);
		return rc;
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
	
	private static boolean validatePrivate(int lastProof, String prevhash, int proof) {
		if (lastProof >= 0) {
			String guess = String.valueOf(lastProof) + prevhash + String.valueOf(proof);
			String hash = DigestUtil.calcHex(guess);
			return hash.startsWith("0000");
		} else {
			return proof == 0;
		}
	}

}
