package mz.co.mahs.models;


public class ItemsPedidos {
	private int idItemsPedido;
	private Producto producto;
	private Pedido pedido;
	private int quantidade;
	private double precoUnitario;
	private  int idp;//forma errada, ainda a pensar na forma certa
	private double subTotal;//forma errada, ainda a pensar na forma certa
	
	public ItemsPedidos() {}

	public ItemsPedidos( int quantidade, double precoUnitario) {
		super();
		
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}



	public int getIdItemsPedido() {
		return idItemsPedido;
	}

	public void setIdItemsPedido(int idItemsPedido) {
		this.idItemsPedido = idItemsPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	

	public int getIdp() {
		return idp;
	}

	public void setIdp(int idp) {
		this.idp = idp;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "ItemsPedidos [idItemsPedido=" + idItemsPedido + ", producto=" + producto + ", pedido=" + pedido
				+ ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + ", idp=" + idp + ", subTotal="
				+ subTotal + "]";
	}

	
	}





	
	
	

	

