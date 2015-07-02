package mirror;

public enum Mirroring {

    HorizontalMirroring("Horizontal Mirroring"),
    VerticalMirroring("Vertical Mirroring"),
    DiagonalMirroring("Diagonal Mirroring");
    
    private String mode;
    
    private Mirroring(String mode) {
        this.mode = mode;
    }
    
    public String getMode(){
        return mode;
    }
    
    
}
