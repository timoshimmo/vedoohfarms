package app.android.vedoohfarms.homepages.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.util.Dialogs.FragmentNotification;
import app.android.vedoohfarms.util.Dialogs.ItemDetailsDialogFragment;
import app.android.vedoohfarms.util.Dialogs.QtyDialogFragment;
import app.android.vedoohfarms.util.adapters.HomeFragmentPageAdapter;
import app.android.vedoohfarms.util.widgets.BadgeArrowDrawerDrawable;
import app.android.vedoohfarms.util.widgets.BadgeDrawable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, QtyDialogFragment.OrderDialogListener,
        ItemDetailsDialogFragment.ItemsDetailDialogListener {

    TextView countMessages;
    Boolean isDrawerOpen = false;

    FragmentNotification fragmentNotification = new FragmentNotification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewPager viewpager = (ViewPager) findViewById(R.id.home_view_pager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.home_tab);
        final RelativeLayout searchContainer = (RelativeLayout) findViewById(R.id.search_container);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final BadgeArrowDrawerDrawable badgeDrawable = new BadgeArrowDrawerDrawable(getSupportActionBar().getThemedContext());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                isDrawerOpen = false;
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isDrawerOpen = true;
                badgeDrawable.setEnabled(false);
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.setDrawerArrowDrawable(badgeDrawable);
        badgeDrawable.setText("");
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        countMessages=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_orders));

        HomeFragmentPageAdapter adapter = new HomeFragmentPageAdapter(MainActivity.this, getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_default).select();
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_tool_default);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_heart_default);
       // tabLayout.getTabAt(3).setIcon(R.drawable.ic_notifications_default);

        initializeCountDrawer();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == 0) {

                    setTitle("Home");

                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_active);

                    if(searchContainer.getVisibility() == View.VISIBLE) {
                        searchContainer.setVisibility(View.GONE);
                    }
                }

                if(tab.getPosition() == 1) {

                    setTitle("Search");

                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_tool_active);

                    if(searchContainer.getVisibility() == View.GONE) {
                        searchContainer.setVisibility(View.VISIBLE);
                    }
                }

                if(tab.getPosition() == 2) {

                    setTitle("Favourites");

                    tabLayout.getTabAt(2).setIcon(R.drawable.ic_heart_active);

                    if(searchContainer.getVisibility() == View.VISIBLE) {
                        searchContainer.setVisibility(View.GONE);
                    }
                }

            /*    if(tab.getPosition() == 3) {

                    setTitle("Notifications");

                    tabLayout.getTabAt(3).setIcon(R.drawable.ic_notifications_active);

                    if(searchContainer.getVisibility() == View.VISIBLE) {
                        searchContainer.setVisibility(View.GONE);
                    }
                } */

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_default).select();
                }

                if(tab.getPosition() == 1) {
                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_tool_default);
                }

                if(tab.getPosition() == 2) {
                    tabLayout.getTabAt(2).setIcon(R.drawable.ic_heart_default);
                }

             /*   if(tab.getPosition() == 3) {
                    tabLayout.getTabAt(3).setIcon(R.drawable.ic_notifications_default);
                }  */


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initializeCountDrawer(){
        //Gravity property aligns the text
        countMessages.setGravity(Gravity.CENTER_VERTICAL);
        countMessages.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        countMessages.setTextColor(getResources().getColor(R.color.custom_email_red));
        countMessages.setTextSize(17);
        countMessages.setText("5");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem itemShoppingbag = menu.findItem(R.id.action_kart);
        LayerDrawable icon = (LayerDrawable) itemShoppingbag.getIcon();
        setBadgeCount(this, icon, "5");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_kart) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentNotification notifyFragments = new FragmentNotification();

            FragmentTransaction transactions = fragmentManager.beginTransaction();

            notifyFragments.show(transactions, "notifyDialog");

            LayerDrawable icon = (LayerDrawable) item.getIcon();
            setBadgeCount(this, icon, "0");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_menu_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_menu_badge, badge);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_orders) {
            countMessages.setText("");

        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_preferences) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected() {

    }
}
