package gr.unirico.mcflib.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.exception.IllegalHashException;
import gr.unirico.mcflib.exception.IllegalPrevidException;
import gr.unirico.mcflib.exception.IllegalProofException;
import gr.unirico.mcflib.exception.IllegalStatusException;

public class Validator {
	private static Logger logger = LoggerFactory.getLogger(Validator.class);

	public static void previd(String previd, String thisPrevid) {
		boolean rc = previd.equals(thisPrevid);
		logger.info("previd: {}, {} -> {}", previd, thisPrevid, rc);
		if (!rc) {
			throw new IllegalPrevidException(previd + "," + thisPrevid);
		}
	}
	
	public static void prevhash(String prevhash, String thisPrevhash) {
		boolean rc = prevhash.equals(thisPrevhash);
		logger.info("prevhash: {}, {} -> {}", prevhash, thisPrevhash, rc);
		if (!rc) {
			throw new IllegalHashException(prevhash + "," + thisPrevhash);
		}
	}
	
	public static void status(String status, String thisStatus) {
		boolean rc = status.equals(thisStatus);
		logger.info("status: {}, {} -> {}", status, thisStatus, rc);
		if (!rc) {
			throw new IllegalStatusException(status + "," + thisStatus);
		}
	}
	
	public static void proof(int lastProof, String prevhash, int proof) {
		boolean rc = ProofOfWork.validate(lastProof, prevhash, proof);
		logger.info("proof: {}, {}, {} -> {}", lastProof, prevhash, proof, rc);
		if (!rc) {
			throw new IllegalProofException(lastProof + "," + proof);
		}
	}
	
	public static void hash(String hash, String thisHash) {
		boolean rc = hash.equals(thisHash);
		logger.info("hash: {}, {} -> {}", hash, thisHash, rc);
		if (!rc) {
			throw new IllegalHashException(hash + "," + thisHash);
		}
	}
	
}
