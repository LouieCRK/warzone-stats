package com.louie.warzonestats.models.player

data class Mode(
    val arena: Arena,
    val arm: Arm,
    val br: Br,
    val br_all: BrAll,
    val br_dmz: BrDmz,
    val conf: Conf,
    val cyber: Cyber,
    val dom: Dom,
    val grnd: Grnd,
    val gun: Gun,
    val hc_conf: HcConf,
    val hc_cyber: HcCyber,
    val hc_dom: HcDom,
    val hc_hq: HcHq,
    val hc_sd: HcSd,
    val hc_war: HcWar,
    val hq: Hq,
    val infect: Infect,
    val koth: Koth,
    val sd: Sd,
    val war: War
)