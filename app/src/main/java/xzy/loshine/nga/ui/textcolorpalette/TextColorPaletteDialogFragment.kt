package xzy.loshine.nga.ui.textcolorpalette

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xzy.loshine.nga.R
import xzy.loshine.nga.di.android.DaggerDialogFragment
import xzy.loshine.nga.di.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TextColorPaletteDialogFragment @Inject constructor() : DaggerDialogFragment() {

    @Inject
    lateinit var adapter: TextColorPaletteAdapter

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context!!).inflate(R.layout.dialog_text_color_palette, null)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 6)
        return AlertDialog.Builder(context!!)
                .setTitle("选择字体颜色")
                .setView(view)
                .setNegativeButton(android.R.string.cancel) { _, _ -> dismiss() }
                .create()
    }
}