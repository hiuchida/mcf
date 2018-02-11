package gr.unirico.mcflib.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.util.DigestUtil;

public class ProofOfWork {
	private static Logger logger = LoggerFactory.getLogger(ProofOfWork.class);

	public static int calc(int lastProof) {
		int proof = 0;
		if (lastProof >= 0) {
			while (!validatePrivate(lastProof, proof)) {
				proof++;
			}
		}
		logger.info("calc: {} -> {}", lastProof, proof);
		return proof;
	}

	public static boolean validate(int lastProof, int proof) {
		boolean rc = validatePrivate(lastProof, proof);
		logger.info("validate: {}, {} -> {}", lastProof, proof, rc);
		return rc;
	}
	
	private static boolean validatePrivate(int lastProof, int proof) {
		if (lastProof >= 0) {
			String guess = String.valueOf(lastProof) + String.valueOf(proof);
			String hash = DigestUtil.calcHex(guess);
			return hash.startsWith("0000");
		} else {
			return proof == 0;
		}
	}

}
