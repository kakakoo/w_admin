package com.i4unetworks.weys.common;

import java.io.FileInputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.i4unetworks.weys.rsv.RsvListVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

@Component
public class GrpPdfView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse response) throws Exception {

		String nm = MapUtils.getString(model, "fileNm");
		@SuppressWarnings("unchecked")
		Map<String, Object> rsvInfo = (Map<String, Object>) model.get("rsvInfo");
		List<RsvListVO> rsvList = (List<RsvListVO>) rsvInfo.get("rsvList");
		String unitText = MapUtils.getString(rsvInfo, "unitText");
		String qr = MapUtils.getString(rsvInfo, "qr");
		String qrCode = MapUtils.getString(rsvInfo, "qrCode");
		int total = MapUtils.getIntValue(rsvInfo, "total");
		String storeNm = MapUtils.getString(rsvInfo, "STORE_NM");
		String rsvDt = MapUtils.getString(rsvInfo, "rsvDt");

		String title = MapUtils.getString(rsvInfo, "title");
		String tp = MapUtils.getString(rsvInfo, "tp");
		
		String fileName = URLEncoder.encode("GROUP_" + nm, "UTF-8");

		String cssNm = req.getSession().getServletContext().getRealPath("/resources/css/pdf.css");
		String font = req.getSession().getServletContext()
				.getRealPath("/resources/fonts/SpoqaHanSansRegular.ttf");

		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);

		PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());
		writer.setInitialLeading(12.5f);

		PdfPageEvent event = new PdfPageEvent();
		writer.setBoxSize("groupPdf", new Rectangle(36,  54,  559,  788));
		writer.setPageEvent(event);
	
		response.setContentType("application/pdf");
		response.setHeader("Content-Transper-Encoding", "binary");
		response.setHeader("Content-Disposition", "inline;filename=" + fileName + ".pdf");

		doc.open();
		XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

		/**
		 * CSS
		 */
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile = helper.getCSS(new FileInputStream(cssNm));
		cssResolver.addCss(cssFile);

		/**
		 * font
		 */
		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		fontProvider.register(font, "fontAwesome");
		CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

		HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

		// Pipelines
		PdfWriterPipeline pdf = new PdfWriterPipeline(doc, writer);
		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

		XMLWorker worker = new XMLWorker(css, true);
		XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));

		// 폰트 설정에서 별칭으로 줬던 "MalgunGothic"을 html 안에 폰트로 지정한다.
		String sHtml = "<html><head></head><body style='font-family: fontAwesome;'>";
		
		sHtml += "	<table class='title_body'>";
		sHtml += "		<tr class='title'>";
		sHtml += "			<td class='title_text left_txt' rowspan='2'>" + title + "</td>";
		sHtml += "			<td rowspan='3' class='barcode'><img src='" + qr + "'/></td>";
		sHtml += "			<td class='sign'>인계자</td>";
		sHtml += "			<td class='sign'>인수자</td>";
		sHtml += "		</tr>";
		sHtml += "		<tr>";
		sHtml += "			<td class='sign sign_space' rowspan='3'></td>";
		sHtml += "			<td class='sign sign_space' rowspan='3'></td>";
		sHtml += "		</tr>";
		sHtml += "		<tr>";
		sHtml += "			<td class='title_text left_txt' rowspan='2'>지점 : " + storeNm + "<br/>년월일 : " + rsvDt + "요일</td>";
		sHtml += "			<td class='barcode'><p class='unit_txt'>" + qrCode + "</p></td>";
		sHtml += "		</tr>";

		sHtml += "		<tr class='smry_txt'>";
		sHtml += "			<td colspan='4' class='title_text left_txt'><b>총 건수 : " + total + "건</b><br/><p class='unit_txt'>" + unitText + "</p></td>";
		sHtml += "		</tr>";
		sHtml += "	</table>";
	
		sHtml += "	<table class='data_body'>";
		sHtml += "		<tr class='data_title'>";
		sHtml += "			<td class='num'>No</td>";
		if(tp.equals("D")){
			sHtml += "			<td class='def'>예약시간</td>";
		} else {
			sHtml += "			<td class='def'>상태</td>";
		}
		sHtml += "			<td class='nm'>성명</td>";
		sHtml += "			<td class='nm'>실명번호</td>";
		sHtml += "			<td class='nm'>교환증번호</td>";
		sHtml += "			<td class='def'>통화</td>";
		sHtml += "			<td class='def'>금액</td>";
		sHtml += "			<td class='def'>비고</td>";
		sHtml += "		</tr>";

		int index = 1;
		for(RsvListVO rsv : rsvList){

			sHtml += "		<tr class='data_list'>";
			sHtml += "			<td class='num'>" + index + "</td>";
			if(tp.equals("D")){
				sHtml += "			<td class='def'>" + rsv.getRsvTm() + "</td>";
			} else {
				sHtml += "			<td class='def'>" + rsv.getRsvSt() + "</td>";
			}
			sHtml += "			<td class='nm'>" + rsv.getRsvNm() + "(" + rsv.getUsrTel()  + ")</td>";
			sHtml += "			<td class='nm'>" + rsv.getRsvNmId() + "</td>";
			sHtml += "			<td class='nm'>" + rsv.getRsvQr() + "</td>";
			sHtml += "			<td class='def'>" + rsv.getUnit() + "</td>";
			sHtml += "			<td class='def'>" + Utils.setStringFormatInteger(rsv.getRsvAmnt() + "") + "</td>";
			sHtml += "			<td class='def'></td>";
			sHtml += "		</tr>";
			index += 1;
		}

		sHtml += "	</table>";
		sHtml += "</body>";
		sHtml += "</html>";

	
		xmlParser.parse(new StringReader(sHtml));
		doc.close();
		writer.close();

	}

}
