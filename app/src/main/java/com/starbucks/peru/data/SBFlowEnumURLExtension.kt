package com.starbucks.peru.data

fun SBFlowEnum.url(/*contextv Context*/): String {
    /*val environment = GHServiceConfig.getEnvironment(context = context)
    val urlBase = environment.baseUrl(context = context).first()
    val urlMaps = "https://maps.googleapis.com/"
    val urlBaseNiubiz = environment.baseUrl(context = context)[1]
    val urlBaseExtornoNiubiz = environment.baseUrl(context = context)[2]*/

    when (this) {
        SBFlowEnum.AccountCreate                   -> return "v1/account/create"
        SBFlowEnum.Settings                        -> return "v1/settings/android"
        SBFlowEnum.OauthToken                      -> return "v1/oauth/token"
        SBFlowEnum.MeProfile                       -> return "v1/me/profile"
        SBFlowEnum.MeDevicesRegister               -> return "v1/me/devices/register"
        SBFlowEnum.MeCardsHistory                  -> return "v1/me/cards/history"
        SBFlowEnum.MeLogout                        -> return "v1/me/logout"
        SBFlowEnum.MeCardsRegisterDigital          -> return "v1/me/cards/register-digital"
        SBFlowEnum.MeCards                         -> return "v1/me/cards"
        SBFlowEnum.MeRewards                       -> return "v1/me/rewards"
        SBFlowEnum.MePaymentMethods                -> return "v1/me/paymentmethods"
        SBFlowEnum.MeAddresses                     -> return "v1/me/addresses"
        SBFlowEnum.MeLoginValidatePassword         -> return "v1/me/login/validate-password"
        SBFlowEnum.MeCardsRegister                 -> return "v1/me/cards/register"
        SBFlowEnum.MeCardsTransfer                 -> return "v1/me/cards/transfer"
        SBFlowEnum.LoginForgotPassword             -> return "v1/login/forgot-password"
        SBFlowEnum.StoresNearby                    -> return "v1/stores/nearby"
        SBFlowEnum.StoresNearest                   -> return "v1/stores/nearest"
        SBFlowEnum.MeProductsEgifts                -> return "v1/me/products"
        SBFlowEnum.Promotions                      -> return "v1/cms/promociones"
        SBFlowEnum.MapsDistanceMatrix              -> return "maps/api/distancematrix/json"
        SBFlowEnum.MapsPlaceAutocomplete           -> return "maps/api/place/autocomplete/json"
        SBFlowEnum.MapsPlaceDetails                -> return "maps/api/place/details/json"
        SBFlowEnum.MapsPlaceNearbySearch           -> return "maps/api/place/nearbysearch/json"
        SBFlowEnum.PromotionsCta                   -> return "v1/cms/cta"
        SBFlowEnum.DeleteAccount                   -> return "v1/account/delete"
        SBFlowEnum.GetTokenNiubiz                  -> return "api.security/v1/security"
        SBFlowEnum.GetNiubiz                       -> return "api.certificate/v1/query"
        SBFlowEnum.GetExtornoNiubiz                -> return "api.authorization/v3/void"
        SBFlowEnum.GetSignatureNiubiz              -> return "api.authorization/v3/retrieve"
    }
}