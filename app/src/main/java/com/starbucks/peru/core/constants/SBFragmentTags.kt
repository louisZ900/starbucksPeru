package com.starbucks.peru.core.constants

const val SB_TAG_FRAGMENT_HOME_CUSTOMER = "tag_fragment_home_customer"
const val SB_TAG_FRAGMENT_CARDS_CUSTOMER = "tag_fragment_cards_customer"

const val SB_TAG_FRAGMENT_HOME_CLIENT = "tag_fragment_home_client"
const val SB_TAG_FRAGMENT_CARDS_CLIENT = "tag_fragment_cards_client"

const val SB_TAG_FRAGMENT_STORES_SHARED = "tag_fragment_stores_shared"
const val SB_TAG_FRAGMENT_GIFT_SHARED = "tag_fragment_gift_shared"
const val SB_TAG_FRAGMENT_ORDER_SHARED = "tag_fragment_order_shared"

const val SB_TAG_FRAGMENT_SETTINGS_CUSTOMER = "tag_fragment_settings_customer"
const val SB_TAG_FRAGMENT_SETTINGS_CLIENT = "tag_fragment_settings_client"

const val SB_TAG_DIALOG_PAY_IN_STORES = "tag_dialog_pay_in_stores"
const val SB_TAG_DIALOG_FILTER_STORES = "tag_dialog_filter_stores"
const val SB_TAG_DIALOG_SEARCH_STORES = "tag_dialog_search_stores"

val  SB_TAGS_RELOAD_FRAGMENTS = listOf(
    SB_TAG_FRAGMENT_HOME_CLIENT,
    SB_TAG_FRAGMENT_GIFT_SHARED
)

const val SB_CLASS_NAME_FRAGMENT_HOME_CUSTOMER = "package com.starbucks.peru.ui.flows.sign_off.home.fragments.SBMainCustomerFragment"
const val SB_CLASS_NAME_FRAGMENT_CARDS_CUSTOMER = "com.starbucks.peru.ui.flows.sign_off.wallet.fragments.SBWalletCustomerFragment"
const val SB_CLASS_NAME_FRAGMENT_SETTINGS_CUSTOMER = "com.starbucks.peru.flows.sign_off.settings.fragments.SBSettingsCustomerFragment"


const val SB_CLASS_NAME_FRAGMENT_HOME_CLIENT = "com.starbucks.peru.flows.sign_on.home.fragments.SBMainClientFragment"
const val SB_CLASS_NAME_FRAGMENT_CARDS_CLIENT_V2 = "com.starbucks.peru.flows.sign_on.wallet.fragments.SBWalletClientContentFragment"
const val SB_CLASS_NAME_FRAGMENT_SETTINGS_CLIENT = "com.starbucks.peru.flows.sign_on.settings.fragments.SBSettingsClientFragment"

const val SB_CLASS_NAME_FRAGMENT_STORES_SHARED  = "com.starbucks.peru.flows.shared.shops.fragments.SBShopsFragment"
const val SB_CLASS_NAME_FRAGMENT_ORDER  = "com.starbucks.peru.flows.shared.order.fragments.OrderFragment"

val SB_HOME_LIST_FRAGMENTS = listOf(
    SB_CLASS_NAME_FRAGMENT_HOME_CUSTOMER,
    SB_CLASS_NAME_FRAGMENT_CARDS_CUSTOMER,
    SB_CLASS_NAME_FRAGMENT_SETTINGS_CUSTOMER,
    SB_CLASS_NAME_FRAGMENT_HOME_CLIENT,
    SB_CLASS_NAME_FRAGMENT_CARDS_CLIENT_V2,
    SB_CLASS_NAME_FRAGMENT_SETTINGS_CLIENT,
    SB_CLASS_NAME_FRAGMENT_STORES_SHARED,
    SB_CLASS_NAME_FRAGMENT_ORDER
)