package gr.unirico.mcflib.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.util.DigestUtil;

public class ProofOfWork {
	private static Logger logger = LoggerFactory.getLogger(ProofOfWork.class);

	public static int calc(int lastProof) {
		logger.info("calc: {}", lastProof);
		int proof = 0;
		if (lastProof >= 0) {
			while (!validatePrivate(lastProof, proof)) {
				proof++;
			}
		}
		return proof;
	}

	public static boolean validate(int lastProof, int proof) {
		logger.info("validate: {}, {}", lastProof, proof);
		return validatePrivate(lastProof, proof);
	}
	
	private static boolean validatePrivate(int lastProof, int proof) {
		if (lastProof >= 0) {
			String guess = String.valueOf(lastProof) + String.valueOf(proof);
			String guess_hash = DigestUtil.calcHex(DigestUtil.SHA256, guess.toString().getBytes());
			return guess_hash.startsWith("0000");
		} else {
			return proof == 0;
		}
	}

}
