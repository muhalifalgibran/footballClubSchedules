package id.alif.footbalmatchschedule.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import id.alif.footbalmatchschedule.model.LastMatchTeam
import id.alif.footbalmatchschedule.R.layout.last_match
import id.alif.footbalmatchschedule.database.Favorite
import id.alif.footbalmatchschedule.presenter.NextMatchPresenter
import id.alif.footbalmatchschedule.R
import id.alif.footbalmatchschedule.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteFragment: Fragment(), LastMatchView {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var presenter: NextMatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var favList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(last_match, container, false)

        favList = view.findViewById<RecyclerView>(R.id.recyclerLastMatch).apply {
            layoutManager = LinearLayoutManager(ctx)
        }

        val listener: (Favorite) -> Unit = {
            startActivity(intentFor<DetailLastMatch>(
                "strEvent" to it.idEvent.toString(),
                "idHomeTeam" to it.idHomeTeam.toString(),
                "idAwayTeam" to it.idAwayTeam.toString()))
        }

        adapter = FavoriteAdapter(favorites, listener)
        favList.adapter = adapter
        swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }

        return view
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            Log.d("hasil", favorite.toString())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamList(data: List<LastMatchTeam>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}