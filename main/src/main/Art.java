
package main;

class Art {
    private String Artist;
    private String Title;
    private String Material;
    private double Price; // Add a price attribute

    public Art(String artist, String title, String material, double price) {
        this.Artist = artist;
        this.Title = title;
        this.Material = material;
        this.Price = price;
    
    }

    public String getArtist() {
        return Artist;
    }

    public String getTitle() {
        return Title;
    }

    public String getMaterial() {
        return Material ;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    @Override
    public String toString() {

        return "\nArt: " + Title.replaceAll("_", " ")
                + "\nDone By by: " + Artist.replaceAll("_", " ")
                + "\nMaterial used: "
                + Material.replaceAll("_", " ")+ 
                "\nArt Price: " + Price + " SAR\n";
    }
}
