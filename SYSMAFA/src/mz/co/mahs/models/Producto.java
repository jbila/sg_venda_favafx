
package mz.co.mahs.models;
/**
 * <h1><b>Producto</b></h1>
 * <p>
 * Esta classe representa o corpo de um Producto, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de Producto
 * </p>
 * 
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */

public class Producto {
	
	private int idProducto;
	private String codigo;
	private String nome;//nome,descricao,qty,precofinal,precoFornecedor,validade,categoria,fornecedor,utilizador,dataRegisto
    private String descricao;
	private int quantidade;
	private double precoFinal;
	private double precoFornecedor;
	/**<p>A validade nao e adicionada no registo e entra na entrada do producto</p>*/
	private String validade;
	private String dataRegisto;
	/*==========================*/
	private Categoria categoria; 
    private Utilizador utilizador;
    private Fornecedor fornecedor;
    /**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
    public  Producto() {}

    /**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}

	public double getPrecoFornecedor() {
		return precoFornecedor;
	}

	public void setPrecoFornecedor(double precoFornecedor) {
		this.precoFornecedor = precoFornecedor;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(String dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * <h2>ToString</h2>
	 * <p>
	 * Pemite a impressao do objecto
	 * </p>
	 */
	@Override
	public String toString() {
		return  getNome();
	}
    
    
  
}
