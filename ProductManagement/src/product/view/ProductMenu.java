package product.view;

import java.util.List;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductMenu {
	private Scanner sc = new Scanner(System.in);
	private ProductController productController = new ProductController();
	public void mainMenu() {
		String menu ="*********상품재고관리 프로그램*********\n"
				+ "1. 전체상품조회\n"
				+ "2. 상품아이디검색\n"
				+ "3. 상품명검색\n"
				+ "4. 상품추가\n"
				+ "5. 상품정보변경\n"
				+ "6. 상품삭제\n"
				+ "7. 상품입/출고 메뉴\n"
				+ "9. 프로그램종료\n"
				+ "***********************************\n";
		while(true) {
			System.out.println(menu);
			String productId = null;
			Product p = null;
			switch(sc.nextInt()) {
			case 1:
				productController.selectAll();
				break;
			case 2:
				productId = inputProductId();
				p = productController.selectOneProduct(productId);
				displayProduct(p);
				break;
			case 3:break;
			case 4:break;
			case 5:
				updateProductMenu();
				break;
			case 6:break;
			case 7:
				productIOMenu();
				break;
			case 9:break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
	}
	private void productIOMenu() {
		String menu = "****** 상품 입출고 메뉴******\r\n" + 
				  "1. 전체입출고내역조회\r\n" + 
				  "2. 상품입고\r\n" + 
				  "3. 상품출고\r\n" + 
				  "9. 메인메뉴 돌아가기\r\n" + 
				  "입력 : ";
	
	
	
	int choice = 0;
//	String productId = null;
//	int amount=0;
//	char io;
	ProductIO productIO = null;
	while(true){
		//회원정보 출력
//		Product p = productController.selectOneProduct(productId);
		//조회된 회원정보가 없는 경우, 리턴
//		if(p == null) return;
		System.out.print(menu);
		choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			productController.selectAll();
			break;
		case 2:
			productIO = inputProductIO();
			productIO.setStatus("I");
			productController.insertProductIO(productIO);
			break;
		case 3:
			productIO = inputProductIO();
			productIO.setStatus("O");
			productController.insertProductIO(productIO);
			break;
		case 9: return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			continue;
		}
		
		
	}
	}
	public void displayProduct(Product p) {
		System.out.println("----------------------------------------------------------------------");
		if(p == null) {
			System.out.println("조회된 상품이 없습니다.");
		}
		else {
			System.out.println(p);
		}
		System.out.println("----------------------------------------------------------------------");
	}
	public void updateProductMenu() {
		String menu = "****** 상품 정보 변경 메뉴******\r\n" + 
					  "1. 상품명변경\r\n" + 
					  "2. 가격변경\r\n" + 
					  "3. 설명변경\r\n" + 
					  "9. 메인메뉴 돌아가기\r\n" + 
					  "입력 : ";
		
		String productId = inputProductId();
		
		int choice = 0;
		while(true){
			//회원정보 출력
			Product p = productController.selectOneProduct(productId);
			//조회된 회원정보가 없는 경우, 리턴
			if(p == null) return;
			
			System.out.print(menu);
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.print("변경할 상품명 : ");
				p.setProductName(sc.next());
				break;
			case 2:
				System.out.print("변경할 가격 : ");
				p.setPrice(sc.nextInt());
				break;
			case 3:
				System.out.print("변경할 설명 : ");
				sc.nextLine();
				p.setDescription(sc.nextLine());
				break;
			case 9: return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			
			productController.updateMember(p);
		}
	}
	private ProductIO inputProductIO() {
		ProductIO productIO = new ProductIO();
		System.out.println("< 상품 입출고 정보 입력 >");
		System.out.println("아이디 : ");
		productIO.setProductId(sc.next());
		System.out.println("수량 : ");  
		productIO.setAmount(sc.nextInt());
		
		return productIO;
	}
	private String inputProductId() {
		System.out.print("아이디 : ");
		return sc.next();
	}
	public void displayProductList(List<Product> list) {
		System.out.println("======================================================");
		//1. 조회된 행이 없는 경우
		if(list == null || list.isEmpty()) {
			System.out.println("조회된 행이 없습니다.");
		}
		//2. 조회된 행이 존재하는 경우
		else {
			for(Product product : list) {
				System.out.println(product);
			}
		}
		System.out.println("======================================================");
	}
	public void displayError(String msg) {
			System.out.println("처리 실패 : " + msg);
			
	}
	public static void displaySuccess(String msg) {
		System.out.println("처리 성공: " + msg);
	}


}
