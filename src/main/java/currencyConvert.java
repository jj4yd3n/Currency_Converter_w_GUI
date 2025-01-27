public class currencyConvert {
    private Double val;
    public currencyConvert(Double val) {
        this.val = val;
    }

    public Double convertUSDToCAD(Double val){
        return val * 1.43;
        //USD to CAD keeps changing wtf so this is a placeholder
    }
    public Double convertUSDToPHP(Double val){
        return val * 58.25;
    }
    public Double convertCADtoUSD(Double val){
        return val / 1.43;
    }
    public Double convertCADtoPHP(Double val){
        return val * 40.64;
    }


}
