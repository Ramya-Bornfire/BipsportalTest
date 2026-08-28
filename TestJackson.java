import com.fasterxml.jackson.databind.ObjectMapper;
public class TestJackson {
    public static class CIMDirectMerchantBenAccount {
        private String MCC;
        public String getMCC() { return MCC; }
        public void setMCC(String mCC) { MCC = mCC; }
    }
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json1 = "{\"MCC\":\"5198\"}";
        String json2 = "{\"mcc\":\"5198\"}";
        try {
            CIMDirectMerchantBenAccount acc1 = mapper.readValue(json1, CIMDirectMerchantBenAccount.class);
            System.out.println("json1 MCC: " + acc1.getMCC());
        } catch(Exception e) {
            System.out.println("json1 failed: " + e.getMessage());
        }
        try {
            CIMDirectMerchantBenAccount acc2 = mapper.readValue(json2, CIMDirectMerchantBenAccount.class);
            System.out.println("json2 MCC: " + acc2.getMCC());
        } catch(Exception e) {
            System.out.println("json2 failed: " + e.getMessage());
        }
    }
}
