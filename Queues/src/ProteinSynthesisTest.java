import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ProteinSynthesisTest {

    @Test
    public void transcribeDNATest() {
        CharQueue transcribed_rna0 = new ProteinSynthesis().transcribeDNA("");
        Assertions.assertTrue(transcribed_rna0.isEmpty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ProteinSynthesis().transcribeDNA("THREE"));

        CharQueue transcribed_rna1 = new ProteinSynthesis().transcribeDNA(
                "CCACGTGCTAAATTAATGCGGCTGCACTGCTCTAAGGACAATTACGGAGTGGGCGGCCTGGCGGGAGCACTACCCCATCGACGCGTACTCGAATACTGT");
        String expected_rna1 = "CCACGUGCUAAAUUAAUGCGGCUGCACUGCUCUAAGGACAAUUACGGAGUGGGCGGCCUGGCGGGAGCACUACCCCAUCGACGCGUACUCGAAUACUGU";
        StringBuilder actual_rna1 = new StringBuilder();
        while (!transcribed_rna1.isEmpty()){
            actual_rna1.append(transcribed_rna1.dequeue());
        }
        Assertions.assertEquals(expected_rna1, actual_rna1.toString());
    }

    @Test
    void translateRNATest() {
        CharQueue protein0_rna = new ProteinSynthesis().transcribeDNA("");
        CharQueue translated_protein0 = new ProteinSynthesis().translateRNA(protein0_rna);
        Assertions.assertTrue(translated_protein0.isEmpty());

        CharQueue protein1_rna = new ProteinSynthesis().transcribeDNA("ATAAAATAATATTAATATAAATTAATTTTATAAAAATATTTATTAATATATAAAAAAATTTAAAATATATTAATTTATATTATTAAAATTATTTAAATA");
        CharQueue translated_protein1 = new ProteinSynthesis().translateRNA(protein1_rna);
        Assertions.assertTrue(translated_protein1.isEmpty());

        CharQueue protein2_rna = new ProteinSynthesis().transcribeDNA("ATGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        CharQueue translated_protein2 = new ProteinSynthesis().translateRNA(protein2_rna);
        String expected_protein2 = "MKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK";
        StringBuilder actual_protein2 = new StringBuilder();
        while (!translated_protein2.isEmpty()){
            actual_protein2.append(translated_protein2.dequeue());
        }
        Assertions.assertEquals(expected_protein2, actual_protein2.toString());

        CharQueue protein3_rna = new ProteinSynthesis().transcribeDNA("ATGGACCATCAGTAGTAGGGATAGTGCCAAAGCTCACTCACCACTGCCTATAAGGGGTGCTTACCTCTAGAATAAGTGTCAGCCAGTATAACCCCATGA");
        CharQueue translated_protein3 = new ProteinSynthesis().translateRNA(protein3_rna);
        String expected_protein3 = "MDHQ*";
        StringBuilder actual_protein3 = new StringBuilder();
        while (!translated_protein3.isEmpty()){
            actual_protein3.append(translated_protein3.dequeue());
        }
        Assertions.assertEquals(expected_protein3, actual_protein3.toString());

        CharQueue protein4_rna = new ProteinSynthesis().transcribeDNA("ACCTATGGGGCTGGATAAAACTGCCCTGGTGACCGCCATCAACAACCCGAATACGTGGCATTTCAGGAGGCGGCCGGAGGGGGGATGTTTTCTACTATTTGACGAGGCCGT");
        CharQueue translated_protein4 = new ProteinSynthesis().translateRNA(protein4_rna);
        String expected_protein4 = "MFSTI*";
        StringBuilder actual_protein4 = new StringBuilder();
        while (!translated_protein4.isEmpty()){
            actual_protein4.append(translated_protein4.dequeue());
        }
        Assertions.assertEquals(expected_protein4, actual_protein4.toString());
    }
}
