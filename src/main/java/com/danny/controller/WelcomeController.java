package com.danny.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.danny.core.Const.Const;
import com.danny.core.Const.ErrorMessage;
import com.danny.core.entity.ProductDetailEntity;
import com.danny.service.CompanyInformationServiceImpl;
import com.danny.service.InventoryService;


@Controller
@RequestMapping("/DannyStorageWebGAE")
@EnableAutoConfiguration
public class WelcomeController {

  private final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

  @Autowired
  private InventoryService inventoryService;

  @Autowired
  private CompanyInformationServiceImpl companyInformationServiceImpl;

  @RequestMapping("/")
  public String index(Model model) {

    LOGGER.debug("index() is executed!");

    model.addAttribute("title", inventoryService.getTitle(""));
    model.addAttribute("msg", inventoryService.getDesc());
    model.addAttribute(Const.JSP_RETURN_FORM_COMPANY_LIST, inventoryService.getCompanyList());

    return "search";
  }

  @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
  public ModelAndView hello(@PathVariable("name") String name) {

    LOGGER.debug("hello() is executed - $name {}" + name);

    ModelAndView model = new ModelAndView();
    model.setViewName("index");

    model.addObject("title", inventoryService.getTitle(name));
    model.addObject("msg", inventoryService.getDesc());

    return model;

  }

  @RequestMapping(value = "/searchProductList", produces = "text/html;charset=UTF-8",
      method = RequestMethod.GET)
  public String searchProductListAction(Model uiModel, HttpServletRequest request,
      HttpServletResponse response) {

    LOGGER.debug("UT_searchAction_000 searchAction Start");

    try {

      HttpSession httpSession = request.getSession();

      // Check Company Name
      String searchCompany = request.getParameter(Const.GET_PARAMETER_SEARCH_COMPANY_NAME);
      LOGGER.debug("UT_searchProductListAction_002 company name");
      if (StringUtils.isEmpty(searchCompany)) {
        LOGGER.debug("UT_searchProductListAction_003 company name error");
        uiModel.addAttribute(Const.ERROR_MESSAGE, ErrorMessage.Error_01);
        return "search";
      }

      // Get ProductList By Company Name
      LOGGER.debug("UT_searchProductListAction_004 search " + searchCompany);

      uiModel.addAttribute(Const.JSP_RETURN_FORM_COMPANY_LIST, inventoryService.getCompanyList());
      uiModel.addAttribute(Const.JSP_RETURN_FORM_PRODUCT_LIST_BY_COMPANY,
          inventoryService.getProductListByCompanyName(searchCompany));
      uiModel.addAttribute(Const.JSP_RETURN_NAME_COMPANY_NAME, searchCompany);

      return "search";

    } catch (Exception e) {

      LOGGER.error("UT_searchAction_006 happened system error");
      LOGGER.error("UT_searchAction_007 error message :" + e.getMessage());
      StackTraceElement[] stackArray = e.getStackTrace();
      for (int i = Const.CONST_INT_0; i < stackArray.length; i++) {
        StackTraceElement element = stackArray[i];
        LOGGER.error("UT_searchAction system Exception: " + element.toString());
      }
      return "errorMessage";

    }
  }

  @RequestMapping(value = "/searchProductDetail", produces = "text/html;charset=UTF-8",
      method = RequestMethod.GET)
  public String searchProductDetailAction(Model uiModel, HttpServletRequest request,
      HttpServletResponse response) {

    LOGGER.debug("UT_searchAction_000 searchAction Start");

    try {

      HttpSession httpSession = request.getSession();

      /*
       * if (commonFunction.checkSession(uiModel, httpSession)) { LOGGER.debug(
       * "UT_searchProductListAction_001 user's information not exist"); return
       * "youngAdminTool/complete"; }
       */

      // Check Company Name
      String searchCompany = request.getParameter(Const.GET_PARAMETER_SEARCH_COMPANY_NAME);
      String searchProduct = request.getParameter(Const.GET_PARAMETER_SEARCH_PRODUCT_NAME);
      LOGGER.debug("UT_searchProductListAction_002 company name");
      if (null == searchCompany || searchCompany.isEmpty() || null == searchProduct
          || searchProduct.isEmpty()) {
        LOGGER.debug("UT_searchProductListAction_003 company name error");

        uiModel.addAttribute(Const.ERROR_MESSAGE, ErrorMessage.Error_01);
        return "search";
      }

      // Get ProductList By Company Name
      LOGGER.debug("UT_searchProductListAction_004 search " + searchCompany);
      ArrayList<ProductDetailEntity> productDetailEntityList =
          companyInformationServiceImpl.getProductDetailByProductName(searchCompany, searchProduct);
      LOGGER.debug("UT_searchAction_005 searchAction end and productListEntityList size is "
          + productDetailEntityList.size());
      if (productDetailEntityList.size() > 0) {
        // if productListEntityList > 0, return those value

        uiModel.addAttribute(Const.JSP_RETURN_FORM_PRODUCT_DETAIL_INFORMATION,
            productDetailEntityList);
        uiModel.addAttribute(Const.JSP_RETURN_NAME_COMPANY_NAME, searchCompany);
        uiModel.addAttribute(Const.JSP_RETURN_NAME_PRODUCT_NAME, searchProduct);

      }
      return "productDetail";

    } catch (Exception e) {

      LOGGER.error("UT_searchAction_006 happened system error");
      LOGGER.error("UT_searchAction_007 error message :" + e.getMessage());
      StackTraceElement[] stackArray = e.getStackTrace();
      for (int i = Const.CONST_INT_0; i < stackArray.length; i++) {
        StackTraceElement element = stackArray[i];
        LOGGER.error("UT_searchAction system Exception: " + element.toString());
      }
      return "errorMessage";

    }
  }


  /*
   * @RequestMapping(value = "/searchProductList", produces = "text/html;charset=UTF-8")
   * 
   * @ResponseBody public String searchProductListAction(@Valid PremiumAdminToolForm
   * premiumAdminToolForm, BindingResult result, HttpServletRequest request) throws ParseException {
   * 
   * // session check HttpSession httpSession = request.getSession();
   * 
   * if (CommonUtil.checkSession(httpSession)) { return format2Jsonp(true,
   * PremiumConst.MESSAGEID_W01034, null); }
   * 
   * 
   * // check company name try { String searchCompany = (String)
   * httpSession.getAttribute(Const.SEARCH_COMPANY_NAME); if (null == searchCompany ||
   * searchCompany.isEmpty()) {
   * 
   * LOGGER.debug("Info_searchProductListAction_001  Search failed, No Company Name"); return
   * handleSearchErrorCase(ErrorMessage.Error_01);
   * 
   * } else { ArrayList<ProductListEntity> productListEntityList =
   * companyInformationServiceImpl.getProductListByCompanyName("詠晨"); if
   * (productListEntityList.size() > 0) { LOGGER.debug(
   * "Info_searchProductListAction_002  Search successfully"); return format2Jsonp(true, null,
   * productListEntityList); } }
   * 
   * } catch (Exception e) { e.printStackTrace(); }
   * 
   * return handleSearchErrorCase(ErrorMessage.Error_02); }
   * 
   * private String format2Jsonp(boolean executeResult, String messageId, PremiumUserEntity
   * premiumUserEntity) { ErrorMessageEntity errorMessageEntity = new ErrorMessageEntity();
   * errorMessageEntity.setSucceed(executeResult); errorMessageEntity.setMessageId(messageId); if
   * (StringUtils.isEmpty(messageId)) { errorMessageEntity.setMessage(null); } else {
   * outputMessage2Log(messageId);
   * errorMessageEntity.setMessage(messages.getSimpleMessage(messageId)); }
   * errorMessageEntity.setPremiumUserEntity(premiumUserEntity);
   * 
   * return Const.JSONP_PREFIX + JSONObject.fromObject(errorMessageEntity).toString() +
   * Const.SYMBOL_BRACKET; }
   * 
   * private String handleSearchErrorCase(String errorCode) {
   * 
   * return format2Jsonp(false, errorCode, null);
   * 
   * }
   */

}
