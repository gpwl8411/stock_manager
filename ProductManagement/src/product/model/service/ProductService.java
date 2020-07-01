package product.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ProductDAO;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductService {
	private ProductDAO productDAO = new ProductDAO();

	public List<Product> selectAll() {
		Connection conn = getConnection();
		List<Product> list = new ProductDAO().selectAll(conn);
		close(conn);
		return list;
	}

	public Product selectOneProduct(String productId) {
		Connection conn = getConnection();
		Product p = productDAO.selectOneProduct(conn,productId);
		close(conn);
		return p;
	}

	public int updateProduct(Product p) {
		Connection conn = getConnection();
		int result = new ProductDAO().updateProduct(conn, p);
		
		if(result>0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertProductIO(ProductIO productIO) {
		Connection conn = getConnection();
		int result = productDAO.insertProductIO(conn,productIO);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}


}
