package com.info5059.casestudy.purchaseOrder;

import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.pdfexample.Generator;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PurchaseOrderPDFGenerator - a class for creating dynamic product purchaseOrder output in
 * PDF format using the iText 7 library
 *
 * @author Edon
 */
public abstract class PurchaseOrderPDFGenerator extends AbstractPdfView {
	public static ByteArrayInputStream generatePurchaseOrder(String repid,
			PurchaseOrderRepository purchaseOrderRepository,
			VendorRepository vendorRepository,
			ProductRepository productRepository) throws IOException {
		URL imageUrl = Generator.class.getResource("/static/images/logo.png");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(baos);
		// Initialize PDF document to be written to a stream not a file
		PdfDocument pdf = new PdfDocument(writer);
		// Document is the main object
		Document document = new Document(pdf);
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		// add the image to the document
		PageSize pg = PageSize.A4;
		Image img = new Image(ImageDataFactory.create(imageUrl)).scaleAbsolute(120, 40)
				.setFixedPosition(pg.getWidth() / 2 - 60, 750);
		document.add(img);
		// now let's add a big heading
		document.add(new Paragraph("\n\n"));
		Locale locale = new Locale("en", "US");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		try {
			BigDecimal tot = new BigDecimal(0.0);
			//BigDecimal sub = new BigDecimal(0.0);
			//BigDecimal tax = new BigDecimal(0.0);
			document.add(new Paragraph("\n"));
			Optional<PurchaseOrder> optPurchaseOrder = purchaseOrderRepository.findById(Long.parseLong(repid));
			if (optPurchaseOrder.isPresent()) {
				PurchaseOrder purchaseOrder = optPurchaseOrder.get();
				document.add(new Paragraph("PurchaseOrder# " + repid).setFont(font).setFontSize(18).setBold()
						.setMarginRight(pg.getWidth() / 2 - 75).setMarginTop(-10)
						.setTextAlignment(TextAlignment.RIGHT));
				document.add(new Paragraph("\n\n"));

				// now a 2 column table
				Table table = new Table(2);
				table.setWidth(new UnitValue(UnitValue.PERCENT, 50));
				// Unfortunately we must format each cell individually :(
				// table headings
				Cell cell = new Cell().add(new Paragraph("Vendor")
						.setFont(font)
						.setFontSize(12)
						.setBold())
						.setTextAlignment(TextAlignment.CENTER);
				table.addCell(cell);
				cell = new Cell()
						.add(new Paragraph(vendorRepository.findById(purchaseOrder.getVendorid()).get().getName())
								.setFont(font)
								.setFontSize(12))
						.setTextAlignment(TextAlignment.LEFT)
						.setBackgroundColor(ColorConstants.GRAY);
				table.addCell(cell);
				table.addCell(new Cell());
				// table details
				cell = new Cell()
						.add(new Paragraph(vendorRepository.findById(purchaseOrder.getVendorid()).get().getAddress1())
								.setFont(font)
								.setFontSize(12)
								.setTextAlignment(TextAlignment.LEFT)
								.setBackgroundColor(ColorConstants.GRAY));
				table.addCell(cell);
				table.addCell(new Cell());
				cell = new Cell()
						.add(new Paragraph(vendorRepository.findById(purchaseOrder.getVendorid()).get().getCity())
								.setFont(font)
								.setFontSize(12)
								.setTextAlignment(TextAlignment.LEFT)
								.setBackgroundColor(ColorConstants.GRAY));
				table.addCell(cell);
				table.addCell(new Cell());
				cell = new Cell()
						.add(new Paragraph(vendorRepository.findById(purchaseOrder.getVendorid()).get().getProvince())
								.setFont(font)
								.setFontSize(12)
								.setTextAlignment(TextAlignment.LEFT)
								.setBackgroundColor(ColorConstants.GRAY));
				table.addCell(cell);
				table.addCell(new Cell());
				cell = new Cell()
						.add(new Paragraph(vendorRepository.findById(purchaseOrder.getVendorid()).get().getEmail())
								.setFont(font)
								.setFontSize(12)
								.setTextAlignment(TextAlignment.LEFT)
								.setBackgroundColor(ColorConstants.GRAY));
				table.addCell(cell);
				Table table2 = new Table(5);
				table2.setWidth(new UnitValue(UnitValue.PERCENT, 100));
				// table total
				cell = new Cell(1, 1).add(new Paragraph("Product Code:")
						.setFont(font)
						.setFontSize(12)
						.setBold()
						.setTextAlignment(TextAlignment.CENTER));
				table2.addCell(cell);
				cell = new Cell(1, 1).add(new Paragraph("Description:")
						.setFont(font)
						.setFontSize(12)
						.setBold()
						.setTextAlignment(TextAlignment.CENTER));
				table2.addCell(cell);
				cell = new Cell(1, 1).add(new Paragraph("Qty Sold:")
						.setFont(font)
						.setFontSize(12)
						.setBold()
						.setTextAlignment(TextAlignment.CENTER));
				table2.addCell(cell);
				cell = new Cell(1, 1).add(new Paragraph("Price:")
						.setFont(font)
						.setFontSize(12)
						.setBold()
						.setTextAlignment(TextAlignment.CENTER));
				table2.addCell(cell);
				cell = new Cell(1, 1).add(new Paragraph("Ext. Price:")
						.setFont(font)
						.setFontSize(12)
						.setBold()
						.setTextAlignment(TextAlignment.CENTER));
				table2.addCell(cell);
				// dump out the line items
				for (PurchaseOrderLineitem line : purchaseOrder.getItems()) {
					Optional<Product> optx = productRepository.findById(line.getProductid());
					if (optx.isPresent()) {
						Product product = optx.get();
						cell = new Cell().add(new Paragraph(product.getId() + "")
								.setFont(font)
								.setFontSize(12))
								.setTextAlignment(TextAlignment.CENTER);
						table2.addCell(cell);
						cell = new Cell().add(new Paragraph(product.getName().toString())
								.setFont(font)
								.setFontSize(12))
								.setTextAlignment(TextAlignment.CENTER);
						table2.addCell(cell);
						cell = new Cell().add(new Paragraph(line.getQty() + "")
						.setFont(font)
						.setFontSize(12))
						.setTextAlignment(TextAlignment.CENTER);
				table2.addCell(cell);
						cell = new Cell().add(new Paragraph(formatter.format(product.getMsrp()))
								.setFont(font)
								.setFontSize(12))
								.setTextAlignment(TextAlignment.RIGHT);
						table2.addCell(cell);
						cell = new Cell().add(new Paragraph(formatter.format(product.getMsrp().multiply(new BigDecimal(line.getQty()))))
								.setFont(font)
								.setFontSize(12))
								.setTextAlignment(TextAlignment.RIGHT);
						table2.addCell(cell);
						tot = tot.add(product.getMsrp().multiply(new BigDecimal(line.getQty())), new MathContext(8, RoundingMode.UP));
					}
				}
				cell = new Cell(1, 4).add(new Paragraph("Sub Total:"))
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.RIGHT);
				table2.addCell(cell);
				cell = new Cell().add(new Paragraph(formatter.format(tot)))
						.setTextAlignment(TextAlignment.RIGHT);
				table2.addCell(cell);
				cell = new Cell(1, 4).add(new Paragraph("Tax:"))
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.RIGHT);
				table2.addCell(cell);
				cell = new Cell().add(new Paragraph(formatter.format(tot.multiply(new BigDecimal(0.13)))))
						.setTextAlignment(TextAlignment.RIGHT);
				table2.addCell(cell);
				// purchaseOrder total
				cell = new Cell(1, 4).add(new Paragraph("Purchase Order Total:"))
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.RIGHT);
				table2.addCell(cell);
				cell = new Cell().add(new Paragraph(formatter.format(tot.multiply(new BigDecimal(0.13)).add(tot))))
						.setTextAlignment(TextAlignment.RIGHT)
						.setBackgroundColor(ColorConstants.YELLOW);
				table2.addCell(cell);
				document.add(table);
				document.add(new Paragraph("\n\n"));
				document.add(table2);
				document.add(new Paragraph("\n\n"));
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
				document.add(new Paragraph(dateFormatter.format(LocalDateTime.now()))
						.setTextAlignment(TextAlignment.CENTER));
			}
			document.close();
		} catch (Exception ex) {
			Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
		}
		// finally send stream back to the controller
		return new ByteArrayInputStream(baos.toByteArray());
	}
}