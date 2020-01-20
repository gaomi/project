package com.company.project.module.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "J302_API_BH_INFO")
public class BhInfo {

    @Id
    @Column(name = "LINE")
    private String line;

    @Column(name = "GQ")
    private String gq;

    @Column(name = "BEGIN")
    private String begin;

    @Column(name = "END")
    private String end;

    @Column(name = "SXX")
    private String sxx;

    @Column(name = "LCB")
    private String lcb;

    @Column(name = "QMB")
    private String qmb;

    @Column(name = "BMB")
    private String bmb;

    @Column(name = "GH")
    private String gh;

    @Column(name = "ZYG")
    private String zyg;

    @Column(name = "SBMC")
    private String sbmc;

    @Column(name = "XLMC")
    private String xlmc;

    @Column(name = "LXMC")
    private String lxmc;

    @Column(name = "BHMS")
    private String bhms;

    @Column(name = "BHDJ")
    private String bhdj;

    @Column(name = "BHL")
    private String bhl;

    @Column(name = "BHDW")
    private String bhdw;

    @Column(name = "BHCJSJ")
    private String bhcjsj;

    @Column(name = "CZRXM")
    private String czrxm;

    @Column(name = "CZRGH")
    private String czrgh;

    @Column(name = "CHZRXM")
    private String chzrxm;

    @Column(name = "CZSJ")
    private String czsj;

    @Column(name = "WXQKSM")
    private String wxqksm;

    @Column(name = "BZ")
    private String bz;

    @Column(name = "XGRXM")
    private String xgrxm;

    @Column(name = "XGSJ")
    private String xgsj;

    @Column(name = "XGFS")
    private String xgfs;

    @Column(name = "BHBH")
    private String bhbh;

    /**
     * @return LINE
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * @return GQ
     */
    public String getGq() {
        return gq;
    }

    /**
     * @param gq
     */
    public void setGq(String gq) {
        this.gq = gq;
    }

    /**
     * @return BEGIN
     */
    public String getBegin() {
        return begin;
    }

    /**
     * @param begin
     */
    public void setBegin(String begin) {
        this.begin = begin;
    }

    /**
     * @return END
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @return SXX
     */
    public String getSxx() {
        return sxx;
    }

    /**
     * @param sxx
     */
    public void setSxx(String sxx) {
        this.sxx = sxx;
    }

    /**
     * @return LCB
     */
    public String getLcb() {
        return lcb;
    }

    /**
     * @param lcb
     */
    public void setLcb(String lcb) {
        this.lcb = lcb;
    }

    /**
     * @return QMB
     */
    public String getQmb() {
        return qmb;
    }

    /**
     * @param qmb
     */
    public void setQmb(String qmb) {
        this.qmb = qmb;
    }

    /**
     * @return BMB
     */
    public String getBmb() {
        return bmb;
    }

    /**
     * @param bmb
     */
    public void setBmb(String bmb) {
        this.bmb = bmb;
    }

    /**
     * @return GH
     */
    public String getGh() {
        return gh;
    }

    /**
     * @param gh
     */
    public void setGh(String gh) {
        this.gh = gh;
    }

    /**
     * @return ZYG
     */
    public String getZyg() {
        return zyg;
    }

    /**
     * @param zyg
     */
    public void setZyg(String zyg) {
        this.zyg = zyg;
    }

    /**
     * @return SBMC
     */
    public String getSbmc() {
        return sbmc;
    }

    /**
     * @param sbmc
     */
    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    /**
     * @return XLMC
     */
    public String getXlmc() {
        return xlmc;
    }

    /**
     * @param xlmc
     */
    public void setXlmc(String xlmc) {
        this.xlmc = xlmc;
    }

    /**
     * @return LXMC
     */
    public String getLxmc() {
        return lxmc;
    }

    /**
     * @param lxmc
     */
    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    /**
     * @return BHMS
     */
    public String getBhms() {
        return bhms;
    }

    /**
     * @param bhms
     */
    public void setBhms(String bhms) {
        this.bhms = bhms;
    }

    /**
     * @return BHDJ
     */
    public String getBhdj() {
        return bhdj;
    }

    /**
     * @param bhdj
     */
    public void setBhdj(String bhdj) {
        this.bhdj = bhdj;
    }

    /**
     * @return BHL
     */
    public String getBhl() {
        return bhl;
    }

    /**
     * @param bhl
     */
    public void setBhl(String bhl) {
        this.bhl = bhl;
    }

    /**
     * @return BHDW
     */
    public String getBhdw() {
        return bhdw;
    }

    /**
     * @param bhdw
     */
    public void setBhdw(String bhdw) {
        this.bhdw = bhdw;
    }

    /**
     * @return BHCJSJ
     */
    public String getBhcjsj() {
        return bhcjsj;
    }

    /**
     * @param bhcjsj
     */
    public void setBhcjsj(String bhcjsj) {
        this.bhcjsj = bhcjsj;
    }

    /**
     * @return CZRXM
     */
    public String getCzrxm() {
        return czrxm;
    }

    /**
     * @param czrxm
     */
    public void setCzrxm(String czrxm) {
        this.czrxm = czrxm;
    }

    /**
     * @return CZRGH
     */
    public String getCzrgh() {
        return czrgh;
    }

    /**
     * @param czrgh
     */
    public void setCzrgh(String czrgh) {
        this.czrgh = czrgh;
    }

    /**
     * @return CHZRXM
     */
    public String getChzrxm() {
        return chzrxm;
    }

    /**
     * @param chzrxm
     */
    public void setChzrxm(String chzrxm) {
        this.chzrxm = chzrxm;
    }

    /**
     * @return CZSJ
     */
    public String getCzsj() {
        return czsj;
    }

    /**
     * @param czsj
     */
    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    /**
     * @return WXQKSM
     */
    public String getWxqksm() {
        return wxqksm;
    }

    /**
     * @param wxqksm
     */
    public void setWxqksm(String wxqksm) {
        this.wxqksm = wxqksm;
    }

    /**
     * @return BZ
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return XGRXM
     */
    public String getXgrxm() {
        return xgrxm;
    }

    /**
     * @param xgrxm
     */
    public void setXgrxm(String xgrxm) {
        this.xgrxm = xgrxm;
    }

    /**
     * @return XGSJ
     */
    public String getXgsj() {
        return xgsj;
    }

    /**
     * @param xgsj
     */
    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    /**
     * @return XGFS
     */
    public String getXgfs() {
        return xgfs;
    }

    /**
     * @param xgfs
     */
    public void setXgfs(String xgfs) {
        this.xgfs = xgfs;
    }

    /**
     * @return BHBH
     */
    public String getBhbh() {
        return bhbh;
    }

    /**
     * @param bhbh
     */
    public void setBhbh(String bhbh) {
        this.bhbh = bhbh;
    }
}