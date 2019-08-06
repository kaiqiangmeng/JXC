package com.wangjiangfei.controller;

import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.entity.PurchaseList;
import com.wangjiangfei.service.PurchaseListGoodsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wangjiangfei
 * @date 2019/8/2 9:10
 * @description 进货商品Controller层
 */
@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {

    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    /**
     * 保存进货单信息
     * @param purchaseList 进货单信息实体
     * @param purchaseListGoodsStr 进货商品信息JSON字符串
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions(value = "进货入库")
    public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr) {
        return purchaseListGoodsService.save(purchaseList, purchaseListGoodsStr);
    }

    /**
     * 查询进货单
     * @param purchaseNumber 单号
     * @param supplierId 供应商ID
     * @param state 付款状态
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions(value={"进货单据查询","供应商统计"},logical = Logical.OR)
    public Map<String,Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime,
                                   String eTime) {
        return  purchaseListGoodsService.list(purchaseNumber, supplierId, state, sTime, eTime);
    }

    /**
     * 查询进货单商品信息
     * @param purchaseListId 进货单ID
     * @return
     */
    @RequestMapping("/goodsList")
    @RequiresPermissions(value={"进货单据查询","供应商统计"},logical = Logical.OR)
    public Map<String, Object> goodsList(Integer purchaseListId) {
        return purchaseListGoodsService.goodsList(purchaseListId);
    }

    /**
     * 删除进货单及商品信息
     * @param purchaseListId 进货单ID
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions(value = "进货单据查询")
    public ServiceVO delete(Integer purchaseListId) {
        return purchaseListGoodsService.delete(purchaseListId);
    }

    /**
     * 修改进货单付款状态
     * @param id 进货单ID
     * @return
     */
//    @RequestMapping("/updateState")
//    @RequiresPermissions(value="供应商统计")
//    public Map<String,Object> updateState(Integer id){
//
//        Map<String,Object> map = new HashMap<String,Object>();
//
//        try {
//
//            purchaseListGoodsService.updateState(1, id);
//
//            logService.save(new Log(Log.DELETE_ACTION, "支付结算进货单："+purchaseListGoodsService.getPurchaseList(id).getPurchaseNumber()));
//
//            map.put("resultCode", ResultCode.SUCCESS);
//
//            map.put("resultContent", "进货单结算成功");
//
//        } catch (Exception e) {
//
//            map.put("resultCode", ResultCode.FAIL);
//
//            map.put("resultContent", "进货单结算失败");
//
//            e.printStackTrace();
//
//        }
//
//        return map;
//
//    }

    /**
     * 进货商品统计
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @param goodsTypeId 商品类别ID
     * @param codeOrName 编号或商品名称
     * @return
     */
//    @RequestMapping("/count")
//    @RequiresPermissions(value="商品采购统计")
//    public String count(String sTime, String eTime ,Integer goodsTypeId, String codeOrName){
//
//        JsonArray result = new JsonArray();
//
//        try {
//
//            List<PurchaseList> purchaseListList = purchaseListGoodsService.getPurchaselist(null, null, null, sTime, eTime);
//
//            for(PurchaseList pl : purchaseListList){
//
//                List<PurchaseListGoods> purchaseListGoodsList = purchaseListGoodsService
//                        .getPurchaseListGoodsByPurchaseListId(pl.getId(), goodsTypeId, codeOrName);
//
//                for(PurchaseListGoods pg : purchaseListGoodsList){
//
//                    JsonObject obj = new JsonObject();
//
//                    obj.addProperty("number", pl.getPurchaseNumber());
//
//                    obj.addProperty("date", pl.getPurchaseDate());
//
//                    obj.addProperty("supplierName", pl.getSupplier().getName());
//
//                    obj.addProperty("code", pg.getCode());
//
//                    obj.addProperty("name", pg.getName());
//
//                    obj.addProperty("model", pg.getModel());
//
//                    obj.addProperty("goodsType", pg.getType().getName());
//
//                    obj.addProperty("unit", pg.getUnit());
//
//                    obj.addProperty("price", pg.getPrice());
//
//                    obj.addProperty("num", pg.getNum());
//
//                    obj.addProperty("total", pg.getTotal());
//
//                    result.add(obj);
//
//                }
//            }
//
//            logService.save(new Log(Log.SELECT_ACTION, "进货商品统计查询"));
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        return result.toString();
//
//    }

}
