package product.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import product.model.exception.ProductException;
import product.model.vo.Product;
import product.model.vo.ProductIO;


public class ProductDAO {
	Properties prop = new Properties();
	public ProductDAO() {
		try {
			prop.load(new FileReader("resources/product-query.properties"));
//			System.out.println("prop@dao ="+prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> selectAll(Connection conn) {
		List<Product> list = null;

		// 사용후 반납해야할(close)자원들은 try~catch문 바깥에서 선언해야 한다.
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAll");
//		System.out.println("query@dao = "+query);

		try {
			// 1. 쿼리문을 실행할 statement객체 생성
			pstmt = conn.prepareStatement(sql);

			// 2. 쿼리문 전송, 실행결과 받기
			rset = pstmt.executeQuery();

			// 3. 받은 결과값들을 객체에 옮겨 저장하기
			list = new ArrayList<Product>();

			while (rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				p.setStock(rset.getInt("stock"));
				list.add(p);
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new ProductException("전체상품조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	

	public Product selectOneProduct(Connection conn, String productId) {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				p.setStock(rset.getInt("stock"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}

	public int updateProduct(Connection conn, Product p) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updateProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getProductName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getDescription());
			pstmt.setInt(4, p.getStock());
			pstmt.setString(5, p.getProductId());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
//			e.printStackTrace();
			throw new ProductException("상품 정보변경 오류!",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertProductIO(Connection conn, ProductIO productIO) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertProductIO"); 
		System.out.println("query@dao = "+query);
		try {
			//Statement객체 준비
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productIO.getProductId());
			pstmt.setInt(2, productIO.getAmount());
			pstmt.setString(3, productIO.getStatus());
			//실행
			result = pstmt.executeUpdate();
			Product p = selectOneProduct(conn,productIO.getProductId());
			if(p.getStock() < 0) {
				return -1;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new ProductException("상품 입출고 오류!",e);
		} finally {			
			close(pstmt);
		}
		System.out.println("result@dao = " + result);
		
		return result;
	}

}
