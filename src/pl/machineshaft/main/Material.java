package pl.machineshaft.main;

public enum Material {
    st5(60000000, 65000000),
    c45(80000000, 85000000),
    c55(90000000, 95000000);
     
    private int kGo;
    private int kSj;
    
    public int getKGo() {
        return kGo;
    }
    
    public int getKSj() {
        return kSj;
    }
    
     
     
    Material(int kGo, int kSj) {
        this.kGo = kGo;
        this.kSj = kSj;
        
    }
    

}
