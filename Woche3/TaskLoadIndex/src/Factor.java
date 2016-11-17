
public class Factor {
	protected String title;
	
	private Double rating;
	private Integer weight = 0;
	private Double product;
	
	public Factor(){
		
	}
	
	public void setRating(Double rating){
		this.rating = rating;
	}
	
	public void increaseWeight(){
		this.weight++;
	}
	
	public Double calculateProduct(){
		this.product = rating*weight;
		return product;
	}
	
	public Integer getWeight(){
		return weight;
	}
}
