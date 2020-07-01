package product.controller;

import java.util.List;

import product.model.exception.ProductException;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductIO;
import product.view.ProductMenu;

public class ProductController {
	private ProductService productService = new ProductService();

	public void selectAll() {
		ProductMenu productMenu = new ProductMenu();
		try {
			List<Product> list = productService.selectAll();
//		System.out.println("list@controller ="+list);
			productMenu.displayProductList(list);

		} catch (ProductException e) {
			// 개발자 디버깅용
			e.printStackTrace();

			// 사용자 알림용
			productMenu.displayError(e.getMessage());
		}
	}

	public Product selectOneProduct(String productId) {
		Product p = productService.selectOneProduct(productId);
		return p;
	}

	public void updateMember(Product p) {

		ProductMenu productMenu = new ProductMenu();

		try {
			int result = productService.updateProduct(p);
			if (result > 0)
				ProductMenu.displaySuccess("상품 정보수정 성공!");
		} catch (ProductException e) {
			productMenu.displayError(e.getMessage());
		}
	}

	public void insertProductIO(ProductIO productIO) {
		ProductMenu memberMenu = new ProductMenu();
		try {

			int result = productService.insertProductIO(productIO);

			if (result > 0)
				memberMenu.displaySuccess("입출고 입력 성공!");

		} catch (ProductException e) {
			e.printStackTrace();
			memberMenu.displayError(e.getMessage());
		}

	}

	public void selectAllIO() {
		ProductMenu productMenu = new ProductMenu();
		try {
			List<ProductIO> list = productService.selectAllIO();
//		System.out.println("list@controller ="+list);
			productMenu.displayProductIOList(list);

		} catch (ProductException e) {
			// 개발자 디버깅용
			e.printStackTrace();

			// 사용자 알림용
			productMenu.displayError(e.getMessage());
		}
	}


}
