public class currencyConvert {
    private Double val;
    public currencyConvert(Double val) {
        this.val = val;
    }

    public Double convertToCAD(Double val){
        return val * 1.43;
        //USD to CAD keeps changing wtf so this is a placeholder
    }


}
