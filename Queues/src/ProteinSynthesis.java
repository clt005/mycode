/*
    Name: Clayton Hammen Tan
    PID:  A17819097
 */

/**
 * Queue Practical Application, DNA Transcription & Translation
 *
 * @author Clayton Hammen Tan
 * @since 04-16-2024
 */
class ProteinSynthesis {
    private static final int GROUP_OF_THREE = 3;
    /**
     * Creates an RNA by replacing all 'T' in the DNA(dna) with 'U'.
     * Saves the result in a queue then returns it.
     * @param dna , the given DNA string
     * @return rna , A queue based from the given DNA transcribed to an RNA
     * @throws IllegalArgumentException , if the given DNA is not divisible by 3
     **/
    public CharQueue transcribeDNA(String dna) {
        if ((dna.length() % GROUP_OF_THREE) != 0){
            throw new IllegalArgumentException();
        }
        CharQueue rna = new CharQueue();
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'T'){
                rna.enqueue('U');
            } else {
                rna.enqueue(dna.charAt(i));
            }
        }
        return rna;
    }

    /**
     * Translates the given RNA queue to a protein queue.
     * Translation starts when codon ("AUG") is found.
     * Translation stops when the sequence is over,
     * or a stopping codon ("UAA", "UAG", "UGA") is found.
     * If not start codon ("AUG") is found return an empty protein queue.
     * @param rna , the given RNA queue
     * @return protein , A queue based on the translation of the given RNA
     */
    public CharQueue translateRNA(CharQueue rna) {
        CharQueue protein = new CharQueue();
        boolean atStart = true;
        StringBuilder codon = new StringBuilder();
        while (!rna.isEmpty()) {
            char nucleotide = rna.dequeue();
            codon.append(nucleotide);
            if (codon.length() == GROUP_OF_THREE) {
                if (atStart && codon.toString().equals("AUG")) {
                    atStart = false;
                }
                if (!atStart) {
                    if (codon.toString().equals("UAA") ||
                            codon.toString().equals("UAG") ||
                            codon.toString().equals("UGA")) {
                        protein.enqueue('*');
                        break;
                    }
                    protein.enqueue(CodonMap.getAminoAcid(codon.toString()));
                }
                codon = new StringBuilder();
            }
        }
        return protein;
    }

}
