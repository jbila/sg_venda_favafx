
package mz.co.mahs.models;
// nao esta sendo usada no momento
public class FornecedorProducto {
	private int idFornecedorProducto;
	private Producto producto;
	private Fornecedor fornecedor;
	
	public FornecedorProducto() {}

	public int getIdFornecedorProducto() {
		return idFornecedorProducto;
	}

	public void setIdFornecedorProducto(int idFornecedorProducto) {
		this.idFornecedorProducto = idFornecedorProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	@Override
	public String toString() {
		
		return super.toString();
	}
}
