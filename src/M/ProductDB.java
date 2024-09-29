package M;

import java.awt.image.BufferedImage;

public class ProductDB
	{
		public int product_id;
		public String product_name;
		public double price_per_unit;
		public String product_description;
		public BufferedImage product_image;

		public ProductDB()
			{
			}

		public ProductDB(int xproduct_id, String xproduct_name, double xprice_per_unit, String xproduct_description,
				BufferedImage xproduct_image)
			{
				product_id = xproduct_id;
				product_name = xproduct_name;
				price_per_unit = xprice_per_unit;
				product_description = xproduct_description;
				product_image = xproduct_image;

			}
	}
