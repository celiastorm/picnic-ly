
package com.picnicly.picnic_ly;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Represents an item in the navigation drawer list.
 */
public class DrawerItem {
	private final OnClickListener mListener;
	private final LinearLayout mView;

	public DrawerItem(LinearLayout view, OnClickListener listener) {
		mView = view;
		mListener = listener;
	}

	/**
	 * Invokes the OnClickListener registered with this DrawerItem.
	 */
	public void onClicked() {
		if (mListener != null) {
			mListener.onClick();
		}
	}

	public View getView() {
		return mView;
	}

	public interface OnClickListener {
		void onClick();
	}
}
