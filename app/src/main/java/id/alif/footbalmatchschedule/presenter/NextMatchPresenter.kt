package id.alif.footbalmatchschedule.presenter

import android.util.Log
import com.google.gson.Gson
import id.alif.footbalmatchschedule.api.ApiRepository
import id.alif.footbalmatchschedule.api.TheSportDBApi
import id.alif.footbalmatchschedule.main.LastMatchView
import id.alif.footbalmatchschedule.model.ResponseApi
import id.alif.footbalmatchschedule.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchPresenter (private val view: LastMatchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getNextMatch(league : String?){

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(league)).await(),
                ResponseApi::class.java)

                view.hideLoading()
                view.showTeamList(data.events)

        }
    }
    fun getNextMatchSearch(league : String?){
        view.showLoading()
        GlobalScope.launch(context.main) {

            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatchSearch(league)).await(),
                ResponseApi::class.java)
            view.hideLoading()
            if (data.event == null){

            }else{
                view.showTeamList(data.event)
            }
        }

    }
}